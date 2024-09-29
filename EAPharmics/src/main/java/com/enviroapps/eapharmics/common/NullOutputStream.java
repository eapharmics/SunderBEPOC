package com.enviroapps.eapharmics.common;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class implements an output stream in which the data is ignored
 * but the length of data in bytes is captured.
 *
 * @author EnviroApps
 * @version $Revison$
 */
public class NullOutputStream extends OutputStream {
 /**
  * The number of valid bytes.
  */
 protected int count;

 /**
  * Flag indicating whether the stream has been closed.
  */
 private boolean isClosed = false;

 /**
  * Creates a new null output stream.
  */
 public NullOutputStream() {
  super();
 }

 /**
  * Writes the specified byte to this output stream.
  *
  * @param b byte to be written.
  */
 public synchronized void write(int b) {
  int newcount = count + 1;
  count = newcount;
 }

 /**
  * Writes bytes from the specified byte array
  * starting at offset to this output stream.
  *
  * @param b data
  * @param off the start offset in the data
  * @param len the number of bytes to write
  */
 public synchronized void write(byte b[], int off, int len) {
  if ((off < 0) || (off > b.length) || (len < 0)
      || ((off + len) > b.length) || ((off + len) < 0)) {
    throw new IndexOutOfBoundsException();
  } else if (len == 0) {
    return;
  }
  int newcount = count + len;
  count = newcount;
 }

 /**
  * Returns the current size of the data buffer.
  *
  * @return current size of the data buffer
  */
 public int size() {
  return count;
 }

 /**
  * Closes this output stream and releases any system resources
  * associated with this stream. A closed stream cannot perform
  * output operations and cannot be reopened.
  */
 public synchronized void close() throws IOException {
  isClosed = true;
 }
}