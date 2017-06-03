package com.dms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.IOUtils;

public final class FileFactory {

	private FileFactory() {
	}

	public static Response getResponse(InputStream in, String filename) {

		if (in == null) {
			return Response.status(200).entity("no data").build();
		}

		Response.ResponseBuilder r = Response.ok(new StreamingOutput() {

			@Override
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				try {
					IOUtils.copy(in, outputStream);
				} finally {
					IOUtils.closeQuietly(in);
				}
			}
		}).type("application/vnd.ms-excel");
		r.header("Content-Disposition", new StringBuilder().append("attachement; filename=").append(filename).append(".xls").toString());
		r.header("Cache-Control", "private,no-cache,no-store");
		r.header("Expires", -1);

		return r.build();
	}

}
