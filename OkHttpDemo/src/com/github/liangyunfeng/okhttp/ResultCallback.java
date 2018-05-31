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
	 * 返回表示此类型实际类型的泛型参数的数组，[0]就是这个泛型参数数组中第一个
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
