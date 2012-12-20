package cn.com.uangel.adsys.util;

/**
 * ajax工具类
 *
 */
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class AjaxUtils {

	/**
	 * 将一组对象序列化为json串并发送到客户端
	 * 
	 * @param objs
	 *            要序列化的目标对象
	 * @throws AjaxException
	 *             将json串发送到客户端时发生了异常
	 */
	public static void sendJSON(HttpServletResponse response, Object... objs) {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsonString(objs));
			// System.out.println(jsonString(objs));
			response.getWriter().close();
		} catch (IOException e) {
			throw new RuntimeException("AjaxException", e);
		}
	}

	/**
	 * 将一组对象序列化为json串
	 * 
	 * @param objs
	 *            要序列化的目标对象
	 * @return 序列化后的json字符串
	 */
	public static String jsonString(Object... objs) {
		if (objs == null)
			return "\"\"";
		if (objs.length == 1)
			return parseJSON(objs[0]);
		return parseJSON(objs);
	}

	/**
	 * 将对象解析为json串
	 * 
	 * @param obj
	 *            要解析的目标对象
	 * @return 解析后的json字符串,用json格式的字符串表示目标对象的属性及值(属性需符合javabean规范,亦即,提供了getter方法)
	 * @throws JSONException
	 *             解析过程中发生了异常
	 */
	@SuppressWarnings("unchecked")
	protected static String parseJSON(Object obj) {
		if (obj == null)
			return "\"\"";
		if (obj.getClass().isPrimitive() || obj instanceof Double || obj instanceof Float || obj instanceof Long
				|| obj instanceof Integer || obj instanceof Short || obj instanceof Byte || obj instanceof Boolean) {
			return obj.toString();
		}
		if (obj instanceof String || obj instanceof Character) {
			if (isJSONString(obj.toString()))
				return obj.toString();
			return "\""
					+ obj.toString().replaceAll("\\\"", "\\\\\"").replaceAll("\\:", "\\\\:").replaceAll("\\{", "\\\\{")
							.replaceAll("\\}", "\\\\}").replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]")
							.replaceAll("\\\n", "\\\\\n").replaceAll("\\\r", "\\\\\r") + "\"";
		}

		if (obj instanceof java.sql.Date) {
			return "\"" + DateUtils.toDateStr((java.util.Date) obj, "yyyy-MM-dd") + "\"";
		}

		if (obj instanceof java.util.Date) {
			return "\"" + DateUtils.toDateStr((java.util.Date) obj, "yyyy-MM-dd HH:mm:ss") + "\"";
		}

		if (obj.getClass().isArray()) {
			StringBuffer buf = new StringBuffer("[");
			int index = 0;
			for (int i = 0; i < Array.getLength(obj); i++)
				buf.append(parseJSON(Array.get(obj, i))).append(++index < Array.getLength(obj) ? "," : "");
			buf.append("]");
			return buf.toString();
		}
		if (obj instanceof Collection) {
			StringBuffer buf = new StringBuffer("[");
			Iterator itr = ((Collection) obj).iterator();
			while (itr.hasNext())
				buf.append(parseJSON(itr.next())).append(itr.hasNext() ? ",\n" : "");
			buf.append("]");
			return buf.toString();
		}
		if (obj instanceof Map) {
			StringBuffer buf = new StringBuffer("{");
			Map objMap = (Map) obj;
			Iterator itr = objMap.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry entry = (Map.Entry) itr.next();
				buf.append("\"" + entry.getKey() + "\"").append(":").append(parseJSON(entry.getValue())).append(
						itr.hasNext() ? ",\n" : "");
			}
			buf.append("}");
			return buf.toString();
		}
		StringBuffer buf = new StringBuffer("{");
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor props[] = beanInfo.getPropertyDescriptors();
			if (props.length <= 0)
				return "";
			int index = 0;
			for (PropertyDescriptor prop : props) {
				if (prop.getName().equals("class") || prop.getReadMethod() == null) {
					++index;
					continue;
				}
				buf.append("\"" + prop.getName() + "\":").append(parseJSON(prop.getReadMethod().invoke(obj))).append(
						++index < props.length ? ",\n" : "");
			}
			buf.append("}");
			return buf.toString();
		} catch (Exception e) {
			throw new RuntimeException("JsonException", e);
		}
	}

	/**
	 * 检测一个字符串是否符合json格式,此处只提供简单的实现
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isJSONString(String s) {
		return s.matches("\\{[^{}]*\\}|\\[[^\\[\\]]*\\]");
	}
}
