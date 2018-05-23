package com.github.liangyunfeng.pattern.adapter;

class Client {
	public static void main(String args[]) {
		ScoreOperation operation; // ��Գ���Ŀ��ӿڱ��
		// operation = (ScoreOperation)XMLUtil.getBean(); //��ȡ�����ļ����������ɶ���
		operation = new OperationAdapter();
		int scores[] = { 84, 76, 50, 69, 90, 91, 88, 96 }; // ����ɼ�����
		int result[];
		int score;

		System.out.println("�ɼ���������");
		result = operation.sort(scores);

		// ��������ɼ�
		for (int i : scores) {
			System.out.print(i + ",");
		}
		System.out.println();

		System.out.println("���ҳɼ�90��");
		score = operation.search(result, 90);
		if (score != -1) {
			System.out.println("�ҵ��ɼ�90��");
		} else {
			System.out.println("û���ҵ��ɼ�90��");
		}

		System.out.println("���ҳɼ�92��");
		score = operation.search(result, 92);
		if (score != -1) {
			System.out.println("�ҵ��ɼ�92��");
		} else {
			System.out.println("û���ҵ��ɼ�92��");
		}
	}
}

// ����ɼ������ࣺĿ��ӿ�
interface ScoreOperation {
	public int[] sort(int array[]); // �ɼ�����

	public int search(int array[], int key); // �ɼ�����
}

// ���������ࣺ������
class QuickSort {
	public int[] quickSort(int array[]) {
		sort(array, 0, array.length - 1);
		return array;
	}

	public void sort(int array[], int p, int r) {
		int q = 0;
		if (p < r) {
			q = partition(array, p, r);
			sort(array, p, q - 1);
			sort(array, q + 1, r);
		}
	}

	public int partition(int[] a, int p, int r) {
		int x = a[r];
		int j = p - 1;
		for (int i = p; i <= r - 1; i++) {
			if (a[i] <= x) {
				j++;
				swap(a, j, i);
			}
		}
		swap(a, j + 1, r);
		return j + 1;
	}

	public void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}

// ���ֲ����ࣺ������
class BinarySearch {
	public int binarySearch(int array[], int key) {
		int low = 0;
		int high = array.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			int midVal = array[mid];
			if (midVal < key) {
				low = mid + 1;
			} else if (midVal > key) {
				high = mid - 1;
			} else {
				return 1; // �ҵ�Ԫ�ط���1
			}
		}
		return -1; // δ�ҵ�Ԫ�ط���-1
	}
}

// ������������������
class OperationAdapter implements ScoreOperation {
	private QuickSort sortObj; // ����������QuickSort����
	private BinarySearch searchObj; // ����������BinarySearch����

	public OperationAdapter() {
		sortObj = new QuickSort();
		searchObj = new BinarySearch();
	}

	public int[] sort(int array[]) {
		return sortObj.quickSort(array); // ������������QuickSort�����򷽷�
	}

	public int search(int array[], int key) {
		return searchObj.binarySearch(array, key); // ������������BinarySearch�Ĳ��ҷ���
	}
}