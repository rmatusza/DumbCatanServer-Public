package com.dumbcatan.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.ErrorManager;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class ResourceLoggerHandler extends FileHandler{

	public ResourceLoggerHandler(String pattern) throws IOException, SecurityException {
		super(pattern);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void publish(LogRecord record) {
		// TODO Auto-generated method stub
		super.publish(record);
	}

	@Override
	public synchronized void close() throws SecurityException {
		// TODO Auto-generated method stub
		super.close();
	}

	@Override
	protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
		// TODO Auto-generated method stub
		super.setOutputStream(out);
	}

	@Override
	public synchronized void setEncoding(String encoding) throws SecurityException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		super.setEncoding(encoding);
	}

	@Override
	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return super.isLoggable(record);
	}

	@Override
	public synchronized void flush() {
		// TODO Auto-generated method stub
		super.flush();
	}

	@Override
	public synchronized void setFormatter(Formatter newFormatter) throws SecurityException {
		// TODO Auto-generated method stub
		super.setFormatter(newFormatter);
	}

	@Override
	public Formatter getFormatter() {
		// TODO Auto-generated method stub
		return super.getFormatter();
	}

	@Override
	public String getEncoding() {
		// TODO Auto-generated method stub
		return super.getEncoding();
	}

	@Override
	public synchronized void setFilter(Filter newFilter) throws SecurityException {
		// TODO Auto-generated method stub
		super.setFilter(newFilter);
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return super.getFilter();
	}

	@Override
	public synchronized void setErrorManager(ErrorManager em) {
		// TODO Auto-generated method stub
		super.setErrorManager(em);
	}

	@Override
	public ErrorManager getErrorManager() {
		// TODO Auto-generated method stub
		return super.getErrorManager();
	}

	@Override
	protected void reportError(String msg, Exception ex, int code) {
		// TODO Auto-generated method stub
		super.reportError(msg, ex, code);
	}

	@Override
	public synchronized void setLevel(Level newLevel) throws SecurityException {
		// TODO Auto-generated method stub
		super.setLevel(newLevel);
	}

	@Override
	public Level getLevel() {
		// TODO Auto-generated method stub
		return super.getLevel();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
}
