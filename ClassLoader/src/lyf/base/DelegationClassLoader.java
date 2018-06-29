package lyf.base;

/**
 * 委托双亲模式
 * 
 * 上面是方法原型，一般实现这个方法的步骤是 1. 执行findLoadedClass(String)去检测这个class是不是已经加载过了。 2.
 * 执行父加载器的loadClass方法。如果父加载器为null，则jvm内置的加载器去替代，也就是Bootstrap ClassLoader。
 * 这也解释了ExtClassLoader的parent为null,但仍然说Bootstrap ClassLoader是它的父加载器。 3.
 * 如果向上委托父加载器没有加载成功，则通过findClass(String)查找。
 * 
 * 如果class在上面的步骤中找到了，参数resolve又是true的话，那么loadClass()又会调用resolveClass(Class)
 * 这个方法来生成最终的Class对象。 我们可以从源代码看出这个步骤。
 */
public class DelegationClassLoader extends ClassLoader {

	private ClassLoader parent;

	@Override
	protected Class<?> findClass(String arg0) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(arg0);
	}

	@Override
	protected Class<?> loadClass(String arg0, boolean arg1)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.loadClass(arg0, arg1);
	}

	/**
	 * 委托双亲模式
	 * 
	 * 上面是方法原型，一般实现这个方法的步骤是 1. 执行findLoadedClass(String)去检测这个class是不是已经加载过了。 2.
	 * 执行父加载器的loadClass方法。如果父加载器为null，则jvm内置的加载器去替代，也就是Bootstrap ClassLoader。
	 * 这也解释了ExtClassLoader的parent为null,但仍然说Bootstrap ClassLoader是它的父加载器。 3.
	 * 如果向上委托父加载器没有加载成功，则通过findClass(String)查找。
	 * 
	 * 如果class在上面的步骤中找到了，参数resolve又是true的话，那么loadClass()又会调用resolveClass(Class)
	 * 这个方法来生成最终的Class对象。 我们可以从源代码看出这个步骤。
	 * 
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// First, check if the class has already been loaded
		// 首先，检测是否已经加载
		Class<?> c = findLoadedClass(name);
		if (c == null) {
			try {
				if (parent != null) {
					// 父加载器不为空则调用父加载器的loadClass
					c = parent.loadClass(name);
				} else {
					// 父加载器为空则调用Bootstrap ClassLoader
					c = findBootstrapClassOrNull(name);
				}
			} catch (ClassNotFoundException e) {
				// ClassNotFoundException thrown if class not found
				// from the non-null parent class loader
			}

			if (c == null) {
				// If still not found, then invoke findClass in order
				// to find the class.
				// 父加载器没有找到，则调用findClass
				c = findClass(name);
			}
		}
		return c;
	}

	private Class<?> findBootstrapClassOrNull(String name) {
		return null;
	}

}
