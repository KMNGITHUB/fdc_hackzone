// versions would be a 1D array with either '0' or '1' as its elements.
// '0' indicates the version is bug-free and '1' indicates the version is buggy.
// (Ex - For input [0, 0, 1, 1, 1], the bug was introduced in version 2 and the function should return 1)

public class Challenge {
    /**
     * The main entry point.
     * Don't change the code, besides the input to the function.
     */
    public static void main(String[] args) throws Exception {
        lastBugFreeVersion(new int[]{0, 0, 1, 1, 1});
    }

    public static int lastBugFreeVersion(int[] versions) {
        int start = 0;
    	int end = versions.length-1;
    	int pos = -1;
    	if(versions.length == 0) {
    		return pos;
    	}
    	while(end >= start) {
    		int mid = (start + end)/2;
    		if(versions[mid] == 0) {
    			pos = mid;
    			start = mid + 1;
    		}else {
    			end = mid - 1;
    		}
    	}
    	return pos;
    }
}