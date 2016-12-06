package seb.domain.bundle;

public interface BundleComparable extends Comparable<BundleComparable> {

	public int resolvePriority();
	
}