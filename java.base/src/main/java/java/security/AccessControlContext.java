/*
 * This code is based on OpenJDK source file(s) which contain the following copyright notice:
 *
 * ------
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
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

package java.security;

import org.qbicc.rt.annotation.Tracking;

@Tracking("java.base/share/java/security/AccessControlContext.java")
public final class AccessControlContext {
    static final ProtectionDomain[] FAKE_CONTEXT = new ProtectionDomain[0];

    public AccessControlContext(ProtectionDomain[] context) {
    }

    public AccessControlContext(AccessControlContext acc, DomainCombiner combiner) {
    }

    AccessControlContext(AccessControlContext acc, DomainCombiner combiner, boolean preauthorized) {
    }

    AccessControlContext(ProtectionDomain caller, DomainCombiner combiner, AccessControlContext parent, AccessControlContext context, Permission[] perms) {
    }

    AccessControlContext(ProtectionDomain[] context, AccessControlContext other) {}

    AccessControlContext(ProtectionDomain[] context, boolean isPrivileged) {}

    public DomainCombiner getDomainCombiner() {
        return null;
    }

    DomainCombiner getCombiner() {
        return null;
    }

    ProtectionDomain[] getContext() {
        return FAKE_CONTEXT;
    }

    public void checkPermission(Permission perm) throws AccessControlException {}

    AccessControlContext optimize() {
        return this;
    }

    public boolean equals(Object obj) {
        return true;
    }

    public int hashCode() {
        return 0;
    }
}
