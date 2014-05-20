package algolithm;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import com.coupang.WEBPS006.hoons.domain.Tag;
//
public class QuickSort {

}
//
//	public static List<Tag> sort(List<Tag> list) {
//
//		int left = 0;
//		int right = list.size() - 1;
//		int pivot = (left + right) / 2;
//
//		interalSort(left, right, pivot, list);
//
//		return list;
//	}
//
//	public static List<Tag> interalSort(int left, int right, int pivot, List<Tag> list) {
//		if (left < right) {
//			int newPivot = partition(left, right, pivot, list);
//			interalSort(left, newPivot - 1, newPivot - 2, list);
//			interalSort(newPivot + 1, right, newPivot + 2, list);
//		}
//		return list;
//	}
//
//	private static int partition(int left, int right, int pivot, List<Tag> list) {
//		int pivotValue = list.get(pivot).getCount();
//		int newPivot = left;
//		swapArr(right, pivot, list);
//		for (int index = left; index < right; index++) {
//			Tag compareTag = list.get(index);
//			if (compareTag.getCount() <= pivotValue) {
//				swapArr(index, newPivot, list);
//				newPivot++;
//			}
//		}
//		swapArr(right, newPivot, list);
//		return newPivot;
//	}
//
//	private static void swapArr(int a, int b, List<Tag> list) {
//		Tag temp = list.get(a);
//		list.set(a, list.get(b));
//		list.set(b, temp);
//	}
//
//	public static void print(List<Tag> list) {
//		for (int index = 0; index < list.size(); index++) {
//			if (index < list.size() - 1) {
//				System.out.print(list.get(index).getCount() + ",");
//			} else {
//				System.out.println(list.get(index).getCount());
//			}
//		}
//	}
//
//	public static void main(String[] argc) {
//		// int[] arr = {5,3,7,6,2,1,4};
//
//		List<Tag> taglist = new ArrayList<Tag>();
//		Random random = new Random();
//		for (int index = 0; index < 100; index++) {
//			taglist.add(new Tag(index + "", random.nextInt(100)));
//		}
//
//		QuickSort.sort(taglist);
//		QuickSort.print(taglist);
//	}
//}
