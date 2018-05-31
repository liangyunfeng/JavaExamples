package com.github.liangyunfeng.okhttp;

import com.google.gson.internal.$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Request;

public abstract class ResultCallback<T> {
	Type mType;

	public ResultCallback() {
		mType = getSuperclassTypeParameter(getClass());
	}

	/**
	 * ���ر�ʾ������ʵ�����͵ķ��Ͳ��������飬[0]����������Ͳ��������е�һ��
	 * 
	 * @param subclass
	 * @return
	 */
	static Type getSuperclassTypeParameter(Class<?> subclass) {
		Type superclass = subclass.getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter");
		}
		ParameterizedType parameterized = (ParameterizedType) superclass;
		return $Gson$Types
				.canonicalize(parameterized.getActualTypeArguments()[0]);
	}

	public abstract void onError(Request request, Exception e);

	public abstract void onResponse(T response);
}
