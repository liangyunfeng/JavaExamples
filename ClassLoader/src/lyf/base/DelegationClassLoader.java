package lyf.base;

/**
 * ί��˫��ģʽ
 * 
 * �����Ƿ���ԭ�ͣ�һ��ʵ����������Ĳ����� 1. ִ��findLoadedClass(String)ȥ������class�ǲ����Ѿ����ع��ˡ� 2.
 * ִ�и���������loadClass�����������������Ϊnull����jvm���õļ�����ȥ�����Ҳ����Bootstrap ClassLoader��
 * ��Ҳ������ExtClassLoader��parentΪnull,����Ȼ˵Bootstrap ClassLoader�����ĸ��������� 3.
 * �������ί�и�������û�м��سɹ�����ͨ��findClass(String)���ҡ�
 * 
 * ���class������Ĳ������ҵ��ˣ�����resolve����true�Ļ�����ôloadClass()�ֻ����resolveClass(Class)
 * ����������������յ�Class���� ���ǿ��Դ�Դ���뿴��������衣
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
	 * ί��˫��ģʽ
	 * 
	 * �����Ƿ���ԭ�ͣ�һ��ʵ����������Ĳ����� 1. ִ��findLoadedClass(String)ȥ������class�ǲ����Ѿ����ع��ˡ� 2.
	 * ִ�и���������loadClass�����������������Ϊnull����jvm���õļ�����ȥ�����Ҳ����Bootstrap ClassLoader��
	 * ��Ҳ������ExtClassLoader��parentΪnull,����Ȼ˵Bootstrap ClassLoader�����ĸ��������� 3.
	 * �������ί�и�������û�м��سɹ�����ͨ��findClass(String)���ҡ�
	 * 
	 * ���class������Ĳ������ҵ��ˣ�����resolve����true�Ļ�����ôloadClass()�ֻ����resolveClass(Class)
	 * ����������������յ�Class���� ���ǿ��Դ�Դ���뿴��������衣
	 * 
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// First, check if the class has already been loaded
		// ���ȣ�����Ƿ��Ѿ�����
		Class<?> c = findLoadedClass(name);
		if (c == null) {
			try {
				if (parent != null) {
					// ����������Ϊ������ø���������loadClass
					c = parent.loadClass(name);
				} else {
					// ��������Ϊ�������Bootstrap ClassLoader
					c = findBootstrapClassOrNull(name);
				}
			} catch (ClassNotFoundException e) {
				// ClassNotFoundException thrown if class not found
				// from the non-null parent class loader
			}

			if (c == null) {
				// If still not found, then invoke findClass in order
				// to find the class.
				// ��������û���ҵ��������findClass
				c = findClass(name);
			}
		}
		return c;
	}

	private Class<?> findBootstrapClassOrNull(String name) {
		return null;
	}

}
