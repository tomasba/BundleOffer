package seb.api.bundle;

public interface BundleComparable extends Comparable<BundleComparable> {

	public int resolvePriority();
	
}