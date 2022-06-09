package sun.nio.ch;

import static org.qbicc.runtime.CNative.addr_of;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;

import jdk.internal.access.JavaNioAccess;
import org.qbicc.runtime.Build;
import org.qbicc.runtime.host.HostIO;
import org.qbicc.runtime.patcher.PatchClass;
import org.qbicc.runtime.patcher.Replace;

/**
 *
 */
@PatchClass(IOUtil.class)
class IOUtil$_patch {
    // alias
    private static JavaNioAccess NIO_ACCESS;

    @Replace
    static int read(FileDescriptor fd, ByteBuffer dst, long position, boolean directIO, boolean async,
                    int alignment, NativeDispatcher nd) throws IOException {
        int pos = dst.position();
        int lim = dst.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        if (dst.isReadOnly()) {
            throw new IllegalArgumentException("Read-only buffer");
        }

        int res;
        if (dst instanceof DirectBuffer) {
            long address = NIO_ACCESS.getBufferAddress(dst);

            // todo: scope?

            if (Build.isHost()) {
                throw new UnsupportedOperationException("Native buffers on host");
            } else {
                if (position != -1) {
                    res = nd.pread(fd, address, rem, position);
                } else {
                    res = nd.read(fd, address, rem);
                }
            }
        } else {
            // heap buffer of some sort
            byte[] array = (byte[]) NIO_ACCESS.getBufferBase(dst);
            int arrayOffset = ((ByteBuffer$_aliases) (Object) dst).offset;

            // todo: scope?

            if (Build.isHost()) {
                if (position != -1) {
                    res = HostIO.pread(((FileDescriptor$_aliases) (Object) fd).fd, array, arrayOffset + pos, rem, position);
                } else {
                    res = HostIO.read(((FileDescriptor$_aliases) (Object) fd).fd, array, arrayOffset + pos, rem);
                }
            } else {
                res = nd.read(fd, addr_of(array[arrayOffset + pos]).longValue(), rem);
            }
        }
        if (res > 0) {
            dst.position(pos + res);
        }
        return res;
    }

}
