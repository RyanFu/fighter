package cn.com.uangel.httpuploadfile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUploadFile {
	private final static String HTTP_ENCODING = "UTF-8";
	private final static int UPLOAD_REQUEST_TIMEOUT = 5 * 1000;
	private final static String HTTP_METHOD = "POST";
	private final static int IO_BUFFER_SIZE = 1024;
	private static final String TWO_HYPHENS = "--";
	private static final String LINEND = "\r\n";
	private static final int HTTP_JSON_TYPE = 0;

	/**
	 * 
	 * sendMultipartDataToHttpServer
	 * 使用post方法请求web服务器，并且当表单数据为：multipart/form-data格式。http请求使用
	 * {@link#HTTP_ENCODING}编码<br/>
	 * 返回json数据，支持文件名中文上传和多文件上传，不支持断点上传，要正确编码服务端返回{@link#HTTP_ENCODING}编码<br/>
	 * 
	 * @param url
	 * @param files
	 *            文件表单域
	 * @param fields
	 *            非文件表单域
	 * @return JSONObject
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static JSONObject sendMultipartDataToHttpServer(URL url,
			final Map<String, File> files, final Map<String, String> fields,
			final UsernamePasswordCredentials credentials) throws IOException,
			JSONException, Exception {
		URL myurl = null;
		String queryString = "";
		// 其他的表单域
		if (fields != null) {
			for (Map.Entry<String, String> entry : fields.entrySet()) {
				queryString += "&"
						+ URLEncoder.encode(entry.getKey(), HTTP_ENCODING)
						+ "="
						+ URLEncoder.encode(entry.getValue(), HTTP_ENCODING);
			}
		}
		if (!queryString.equals("")) {
			queryString = queryString.replaceFirst("&", "?");
		} else {
		}

		myurl = new URL(url.getProtocol(), url.getHost(), url.getPort(),
				url.getPath() + queryString);
		HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
		conn.setConnectTimeout(UPLOAD_REQUEST_TIMEOUT);
		conn.setRequestMethod(HTTP_METHOD);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);

		String boundary = "laohuidi_" + java.util.UUID.randomUUID().toString()
				+ "_laohuidi";
		conn.setRequestProperty(
				"Accept",
				"image/gif,image/x-xbitmap,image/jpeg,image/pjpeg,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword,application/x-shockwave-flash,application/x-quickviewplus,*/*");
		conn.setRequestProperty("keep-alive", "300");
		conn.setRequestProperty(
				"user-agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 GTB6");
		conn.setRequestProperty("accept-language", "zh-cn,zh;q=0.5");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="
				+ boundary);

		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		// 乱码问题 可以试下 PrintWriter out = new PrintWriter(new
		// OutputStreamWriter(connection.getOutputStream(),"utf-8"));
		dos = new DataOutputStream(conn.getOutputStream());
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = IO_BUFFER_SIZE;
		String tem = "";
		if (files != null)
			for (Map.Entry<String, File> entry : files.entrySet()) {
				// 分隔符开头
				dos.writeBytes(TWO_HYPHENS + boundary + LINEND);
				// create a buffer of maximum size
				FileInputStream fileInputStream = new FileInputStream(
						entry.getValue());
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];
				// read file and write it into form...
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				tem = entry.getValue().getName();
				dos.writeBytes("Content-Disposition:form-data;name=\""
						+ entry.getKey() + "\";" + "filename=\"");
				dos.writeUTF(tem);// 中文的文件名使用这里
				dos.writeBytes("\"" + LINEND);
				dos.writeBytes("Content-Type:image/jpg" + LINEND + LINEND);// 类型的判断暂时不处理
				while (bytesRead > 0) {
					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				}
				// close streams
				fileInputStream.close();
				dos.writeBytes(LINEND);
			}
		// http 结束符
		dos.writeBytes(TWO_HYPHENS + boundary + TWO_HYPHENS);
		dos.writeBytes(LINEND);

		dos.flush();
		dos.close();
		// 返回类型
		String responseType = conn.getHeaderField("Content-Type");
		// 正常返回而且必须为json类型
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK
				&& responseType != null
				&& responseType.indexOf(HTTP_JSON_TYPE) >= 0) {
			responseType = (convertStreamToString(conn.getInputStream()));

		} else {
			responseType = "{}";
		}
		try {
			conn.disconnect();
		} catch (Exception e) {
		}
		return new JSONObject(responseType);
	}

	public static String convertStreamToString(InputStream is)
			throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				HTTP_ENCODING));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
