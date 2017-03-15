package Java;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc: 社会信用代码证 检验
 * Author: Yehu
 * Created by kachesiji on 2016/5/21.
 */
class Regex_CreditCode {
//    百度所得91350200M000000510,91350128M00000019A待调试
    static String[] creditCode = {
        "91440300311906854A",
        "91110105771583283D","91110000802100433B","91110108740084270C",
        "112302070019030687","91110108735106340Y","911101077776591113",
        "51610700305332460K","91440300359143307G","11610923MB2900168E",
        "1134082855783407XL","91341000MA2N0ABGXX","91110228397176695A",
        "91110111567495091J","51100000500019541K","11330283002982634F",
        "92510100MA61WJ3204","52610500MJU60350X1","91510000779827684C",
        "1142092201131905X1","91350100M000100Y43","51140200MJ1693047P",
        "913101135696449862","91460600MA5RC30P9Y","11991000010647510A",
        "91110113766754543Y","121526004609138637","52440111MJK9980480",
        "91330200750364874C","123456789012345678","52120000MJ0529018E"
};
    static String isCreditCode = "true";
    static String error_CreditCode = "社会信用代码有误";
    static String error_CreditCode_min = "社会信用代码不足18位，请核对后再输！";
    static String error_CreditCode_max = "社会信用代码大于18位，请核对后再输！";
    static String error_CreditCode_empty ="社会信用代码不能为空！";
    private static Map<String,Integer> datas = null;
    private static char[] pre17s;
    static int[] power = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};
    static char[] code = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y'};
   public  static  void main(String[] args){
       String temp = creditCode[0];
       System.out.println(temp);
       initDatas(code.length);
       pre17(temp);
       isCreditCode(temp);
    }

    /**
     * 判断是否是一个有效的社会信用代码
     * @param creditCode
     * @return
     */
    static String isCreditCode(String creditCode){
        if("".equals(creditCode)||" ".equals(creditCode)){
            System.out.println(error_CreditCode_empty);
            return error_CreditCode_empty;
        }else if(creditCode.length()<18){
            System.out.println(error_CreditCode_min);
            return  error_CreditCode_min;
        }else if(creditCode.length()>18){
            System.out.println(error_CreditCode_max);
            return  error_CreditCode_max;
        }else{
            int sum =  sum(pre17s);
            int temp = sum%31;
            temp = temp == 0 ? 31 : temp;
            System.out.println(code[31-temp]+" "+(creditCode.substring(17,18).equals(code[31-temp]+"")?isCreditCode:error_CreditCode));
            return creditCode.substring(17,18).equals(code[31-temp]+"")?isCreditCode:error_CreditCode;
        }
    }

    /**
     * @param chars
     * @return
     */
    private static int sum(char[] chars){
        int sum = 0;
        for(int i=0;i<chars.length;i++){
            int code = datas.get(chars[i]+"");
            sum+=power[i]*code;
        }
        return sum;

    }

    /**
     * 获取前17位字符
     * @param creditCode
     */
    static  void  pre17(String creditCode){
        String pre17 = creditCode.substring(0,17);
        pre17s = pre17.toCharArray();
    }

    /**
     * 初始化数据
     * @param count
     */
    static void  initDatas(int count){
        datas = new HashMap<>();
        for(int i=0;i<code.length;i++){
            datas.put(code[i]+"",i);
        }
    }
}
