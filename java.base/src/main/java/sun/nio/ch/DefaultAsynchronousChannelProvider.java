/*
 * This code is based on OpenJDK source file(s) which contain the following copyright notice:
 *
 * ------
 * Copyright (c) 2008, 2009, 2017, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 * ------
 *
 * This file may contain additional modifications which are Copyright (c) Red Hat and other
 * contributors.
 */

package sun.nio.ch;

import java.nio.channels.spi.AsynchronousChannelProvider;

import org.qbicc.runtime.Build;
import org.qbicc.rt.annotation.Tracking;

@Tracking("java.base/aix/classes/sun/nio/ch/DefaultAsynchronousChannelProvider.java")
@Tracking("java.base/linux/classes/sun/nio/ch/DefaultAsynchronousChannelProvider.java")
@Tracking("java.base/macosx/classes/sun/nio/ch/DefaultAsynchronousChannelProvider.java")
@Tracking("java.base/windows/classes/sun/nio/ch/DefaultAsynchronousChannelProvider.java")
public class DefaultAsynchronousChannelProvider {
    /**
     * Prevent instantiation.
     */
    private DefaultAsynchronousChannelProvider() { }

    /**
     * Returns the default AsynchronousChannelProvider.
     */
    public static AsynchronousChannelProvider create() {
        if (Build.Target.isAix()) {
            return new AixAsynchronousChannelProvider();
        } else if (Build.Target.isLinux()) {
            return new LinuxAsynchronousChannelProvider();
        } else if (Build.Target.isMacOs()) {
            return new BsdAsynchronousChannelProvider();
        } else if (Build.Target.isWindows()) {
            return new WindowsAsynchronousChannelProvider();
        } else {
            throw new Error();
        }
    }
}
