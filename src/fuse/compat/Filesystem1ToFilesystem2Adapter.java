/**
 *   FUSE-J: Java bindings for FUSE (Filesystem in Userspace by Miklos Szeredi (mszeredi@inf.bme.hu))
 *
 *   Copyright (C) 2003 Peter Levart (peter@select-tech.si)
 *
 *   This program can be distributed under the terms of the GNU LGPL.
 *   See the file COPYING.LIB
 */
package fuse.compat;

import java.nio.ByteBuffer;

import fuse.FuseException;
import fuse.FuseStatfs;

/**
 * This is an adapter that adapts the oldest path-only based compatibility
 * filesystem API (fuse.compat.Filesystem1) to the old filehandle enabled API
 * (fuse.compat.Filesystem2)
 */
public class Filesystem1ToFilesystem2Adapter implements Filesystem2 {
	Filesystem1 fs1;

	public Filesystem1ToFilesystem2Adapter(Filesystem1 fs1) {
		this.fs1 = fs1;
	}

	//
	// Filesystem implementation

	@Override
	public FuseStat getattr(String path) throws FuseException {
		return fs1.getattr(path);
	}

	@Override
	public String readlink(String path) throws FuseException {
		return fs1.readlink(path);
	}

	@Override
	public FuseDirEnt[] getdir(String path) throws FuseException {
		return fs1.getdir(path);
	}

	@Override
	public void mknod(String path, int mode, int rdev) throws FuseException {
		fs1.mknod(path, mode, rdev);
	}

	@Override
	public void mkdir(String path, int mode) throws FuseException {
		fs1.mkdir(path, mode);
	}

	@Override
	public void unlink(String path) throws FuseException {
		fs1.unlink(path);
	}

	@Override
	public void rmdir(String path) throws FuseException {
		fs1.rmdir(path);
	}

	@Override
	public void symlink(String from, String to) throws FuseException {
		fs1.symlink(from, to);
	}

	@Override
	public void rename(String from, String to) throws FuseException {
		fs1.rename(from, to);
	}

	@Override
	public void link(String from, String to) throws FuseException {
		fs1.link(from, to);
	}

	@Override
	public void chmod(String path, int mode) throws FuseException {
		fs1.chmod(path, mode);
	}

	@Override
	public void chown(String path, int uid, int gid) throws FuseException {
		fs1.chown(path, uid, gid);
	}

	@Override
	public void truncate(String path, long size) throws FuseException {
		fs1.truncate(path, size);
	}

	@Override
	public void utime(String path, int atime, int mtime) throws FuseException {
		fs1.utime(path, atime, mtime);
	}

	@Override
	public FuseStatfs statfs() throws FuseException {
		return fs1.statfs();
	}

	@Override
	public long open(String path, int flags) throws FuseException {
		fs1.open(path, flags);

		// Filesystem1 doesn't support filehandles
		return 0;
	}

	@Override
	public void read(String path, long fh, ByteBuffer buf, long offset)
			throws FuseException {
		fs1.read(path, buf, offset);
	}

	@Override
	public void write(String path, long fh, boolean isWritepage,
			ByteBuffer buf, long offset) throws FuseException {
		fs1.write(path, buf, offset);
	}

	@Override
	public void flush(String path, long fh) throws FuseException {
		// no flush operation in Filesystem1 - ignore
	}

	@Override
	public void release(String path, long fh, int flags) throws FuseException {
		fs1.release(path, flags);
	}

	@Override
	public void fsync(String path, long fh, boolean isDatasync)
			throws FuseException {
		// no fsync operation in Filesystem1 - ignore
	}
}
