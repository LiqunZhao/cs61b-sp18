package hw3.hash;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class OomageTestUtility {

    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        Map<Integer, Integer> bucket2Num = new HashMap<>();
        for (Oomage oomage : oomages) {
            int hashCode = oomage.hashCode();
            int bucket = (hashCode & 0x7FFFFFFF) % M;
            if (bucket2Num.containsKey(bucket)) {
                int num = bucket2Num.get(bucket);
                bucket2Num.put(bucket, num + 1);
            } else {
                bucket2Num.put(bucket, 1);
            }
        }

        int N = oomages.size();
        for (int bucket : bucket2Num.keySet()) {
            int num = bucket2Num.get(bucket);
            if (num >= N / 2.5 || num <= N / 50) {
                return false;
            }
        }
        return true;
    }

}
