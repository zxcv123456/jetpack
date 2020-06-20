package com.example.jetpack.utils;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
    public final static String UTF_8 = "utf-8";
    public static Map<String, String> NewParamMap = new CheckParamMap();
    
     /*for (Map.Entry<String, String> entry : map.entrySet()) {
        LogUtils.d("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }*/

    /**
     * @param strs
     * @return true:有参数为�?
     */
    public static boolean IsKong(String... strs) {
        if (strs == null || strs.length <= 0) {
            return true;
        }
        for (String str : strs) {
            if (str == null || str.equals("") || str.equals("NULL") || str.equals("null")) {
                //Toast.makeText(UIUtils.getContext(), str"", 0).show();
                return true;
            }
        }
        return false;
    }

    public static boolean allIsNotKong(String... strs) {
        if (strs == null || strs.length <= 0) {
            return false;
        }
        for (String str : strs) {
            if (str == null || str.equals("") || str.equals("NULL") || str.equals("null")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否只包含汉字、字母、数字和下划线
     *
     * @param text
     * @return
     */
    public static boolean checkNickname(String text) {
        final String format = "[^\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-_]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
    }

    /**
     * 截取数字类的String小数点前面的数字String
     *
     * @param text 需要截取的字符串
     * @return
     */
    public static String textFromFoint(String text) {
        try {
            if (text.contains(".")) {
                String trunc_gamesize = text.substring(0, text.indexOf("."));
                text = trunc_gamesize;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("截取String类的小数点之前的字符串出错：" + e);
        }
        return text;
    }

    /**
     * 旧的窜
     *
     * @param map
     * @return
     */
    /*public static Map<String, String> getParamSortSign1(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.HHKHDHJF (), 1);
    }

    *//**
     * 应用市场接口新的窜,没有走异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getParamSortSign2(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.MARKET_NEWCUAN (), 2);
    }

    *//**
     * 应用市场接口新的串,异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getYHParamSortSign2(Map<String, String> map) {

        return getYiParamSortSign (map, PrivateKeyOrConstant.MARKET_NEWCUAN (), 2, 3);
    }

    *//**
     * 用户中心相关的cuan：UserCenter_Cuan，没有异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> UserCenterGetParamSortSign(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.UserCenter_Cuan (), 2);
    }

    *//**
     * 用户中心相关的cuan：UserCenter_Cuan,异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> UserCenterYHGetParamSortSign(Map<String, String> map) {
        return getYiParamSortSign (map, PrivateKeyOrConstant.UserCenter_Cuan (), 2, 4);
    }

    *//**
     * 小号交易相关的cuan：DEALGDGD
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> dealGetParamSortSign(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.DEALGDGD (), 2);
    }

    *//**
     * h5 分享的窜
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getParamSortSignH5(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.H5_SHARE (), 2);
    }

    *//**
     * 支付宝和银联走官方支付,没有走异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getParamSortSignPayoffi(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.PayOffi_Cuan (), 2);
    }

    *//**
     * 交易买号官方支付,没有走异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getDealParamSortSignPayoffi(Map<String, String> map) {
        return getParamSortSign (map, PrivateKeyOrConstant.DEALGDGD (), 2);
    }

    *//**
     * 交易买号官方支付,走异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getYHDealParamSortSignPayoffi(Map<String, String> map) {
        return getYiParamSortSign (map, PrivateKeyOrConstant.DEALGDGD (), 2, 2);
    }

    *//**
     * h5新官方支付，走异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getYHParamSortSign(Map<String, String> map) {
        return getYiParamSortSign (map, PrivateKeyOrConstant.PayOffi_Cuan (), 2, 0);
    }

    *//**
     * h5新接口，走异或处理
     *
     * @param map
     * @return
     *//*
    public static Map<String, String> getYH5NewParamSortSign(Map<String, String> map) {
        return getYiParamSortSign (map, PrivateKeyOrConstant.H5_SHARE (), 2, 1);
    }

    *//**
     * 返回一个 带有sign 已经排好序列的map
     *
     * @return
     *//*
    public static Map<String, String> getParamSortSign(Map<String, String> ParamMap, String pri, int State) {
        String s = "";
        if (State == 2) {
            String randomNum = RandomNumberUtils.getChar (32);
            String timeStamp;
//            synchronized (StrUtils.class) {
            timeStamp = System.currentTimeMillis () + "";
//            }
            ParamMap.put ("randomNum", randomNum);

            ParamMap.put ("timeStamp", timeStamp);
        }
        List<Map.Entry<String, String>> list = new ArrayList<> (ParamMap.entrySet ());
        Collections.sort (list, new Comparator<Map.Entry<String, String>> () {
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                return o1.getKey ().compareTo (o2.getKey ());
            }
        });
        int size = list.size ();
        switch (State) {
            case 1:
                for (int i = 0; i < size; i++) {
                    Entry<String, String> entry = list.get (i);
                    String value = entry.getValue ();
                    s += value;
                }
                break;
            case 2:
                try {
                    for (int i = 0; i < size; i++) {
                        Entry<String, String> entry = list.get (i);
                        String key = entry.getKey ();
                        String value = entry.getValue ();
                        if (i != 0) {
                            s += "&" + key + "=" + value;
                        } else {
                            s += key + "=" + value;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                break;
            default:
                break;
        }
        s += pri;
        //LogUtils.e("s："+s);
        ParamMap.put ("sign", Md5Utils.encode (s));

        return ParamMap;
    }*/


    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 返回一个高亮spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return 高亮spannable
     */
   /* public static CharSequence getHighLightText(String content, int color,
                                                int start, int end) {
        if (StrUtils.IsKong (content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length () ? end : content.length ();
        SpannableString spannable = new SpannableString (content);
        CharacterStyle span = new ForegroundColorSpan (color);
        spannable.setSpan (span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }*/

    /**
     * 获取链接样式的字符串，即字符串下面有下划线
     *
     * @param resId 文字资源
     * @return 返回链接样式的字符串
     */
    /*public static Spanned getHtmlStyleString(int resId) {
        StringBuilder sb = new StringBuilder ();
        sb.append ("<a href=\"\"><u><b>").append (UIUtils.getString (resId))
                .append (" </b></u></a>");
        return Html.fromHtml (sb.toString ());
    }*/

    /**
     * 格式化文件大小，不保留末尾的0
     */
    /*public static String formatFileSize(long len) {
        return formatFileSize (len, false);
    }*/

    /**
     * 格式化文件大小，保留末尾的0，达到长度一致
     */
    /*public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat ("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat ("#.0");
        if (len < 1024) {
            size = String.valueOf (len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf (len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf (len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf (len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf (formatKeepTwoZero.format (len * 100 / 1024
                        / 1024 / (float) 100))
                        + "MB";
            } else {
                size = String.valueOf (len * 100 / 1024 / 1024 / (float) 100)
                        + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf (formatKeepOneZero.format (len * 10 / 1024
                        / 1024 / (float) 10))
                        + "MB";
            } else {
                size = String.valueOf (len * 10 / 1024 / 1024 / (float) 10)
                        + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf (len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf (len * 100 / 1024 / 1024 / 1024 / (float) 100)
                    + "GB";
        }
        return size;
    }*/

    /**
     * java 计算包含中文字符串的真实长度
     *
     * @param str
     * @return 返回的是字符串的长度值(int)
     */
    public static int getRealLength(String str) {
        int m = 0;
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            // 中文字符(根据Unicode范围判断),中文字符长度为2
            if ((c >= 0x0391 && c <= 0xFFE5)) {
                m = m + 2;
            } else if ((c >= 0x0000 && c <= 0x00FF)) // 英文字符
            {
                m = m + 1;
            }
        }
        return m;
    }

    public static boolean setNull(Object... objs) {
        try {
            if (objs != null) {
                for (int i = 0; i < objs.length; i++) {
                    Object obj = objs[i];
                    if (obj != null) {
                        obj = null;
                    }
                    if (i == objs.length - 1) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e("对象销毁置空出错setNull e:" + e.toString());
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 把不为空的集合，重置为空
     *
     * @param objs
     * @return
     */
    public static boolean clear(Object... objs) {
        try {
            if (objs != null) {
                for (int i = 0; i < objs.length; i++) {
                    Object obj = objs[i];
                    if (obj != null) {
                        if (obj instanceof List) {
                            List a = (List) obj;
                            a.clear();
                        } else if (obj instanceof Map) {
                            Map a = (Map) obj;
                            a.clear();
                        } else {

                        }
                    }
                    if (i == objs.length - 1) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e("对象销毁清空 clear e:" + e.toString());
            e.printStackTrace();
            return false;
        }
        return false;
    }

    //集成了产生encode字段后，在去走获取sign
    /*public static Map<String, String> getYiParamSortSign(Map<String, String> ParamMap, String pri, int State, int getParamSortSignFunTag) {
        String s = "";
        Map<String, String> ParamSortSign = null;
        if (NewParamMap == null) {
            NewParamMap = new CheckParamMap ();
        }

        boolean empty = NewParamMap.isEmpty ();
        if (!empty) {
            NewParamMap.clear ();
        }
        synchronized (NewParamMap) {
            List<Map.Entry<String, String>> list = new ArrayList<> (ParamMap.entrySet ());
            Collections.sort (list, (o1, o2) -> {
                Random random = new Random ();
                int nextInt = random.nextInt ();
                return (nextInt % 3 - 1);
            });
            String tempPri = pri;
            switch (State) {
                case 2:
                    try {
                        int size = list.size ();
                        for (int i = 0; i < size; i++) {
                            Entry<String, String> entry = list.get (i);
                        *//*LogUtils.i("entry.getKey():"+entry.getKey());
                        LogUtils.i("entry.getValue():"+entry.getValue());*//*
                            String key = URLEncoder.encode (entry.getKey (), "UTF-8");
                            String value = URLEncoder.encode (entry.getValue (), "UTF-8");
                            if (i != 0) {
                                s += "&" + key + "=" + value;
                            } else {
                                s += key + "=" + value;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println ("异常：e90：" + e.toString ());
                    }
                    break;
            }
            String yiEncode = yiEncode (s, tempPri);
            NewParamMap.put ("auth_string", yiEncode);
            if (getParamSortSignFunTag == 0) {
                ParamSortSign = getParamSortSignPayoffi (NewParamMap);
            } else if (getParamSortSignFunTag == 1) {
                ParamSortSign = getParamSortSignH5 (NewParamMap);
            } else if (getParamSortSignFunTag == 2) {
                ParamSortSign = getDealParamSortSignPayoffi (NewParamMap);
            } else if (getParamSortSignFunTag == 3) {
                ParamSortSign = getParamSortSign2 (NewParamMap);
            } else if (getParamSortSignFunTag == 4) {
                ParamSortSign = UserCenterGetParamSortSign (NewParamMap);
            } else if (getParamSortSignFunTag == 5) {
                ParamSortSign = getParamSortSign (NewParamMap, PrivateKeyOrConstant.TOPIC_REMARK (), 2);
            }
            Map<String, String> map = ParamSortSign;
            return map;
        }
    }

    public static String yiEncode(String key, String cuan) {
        String endoce = "";
        try {
            synchronized (StrUtils.class) {

                byte[] bytes = key.getBytes ("UTF-8");
                if (StrUtils.IsKong (key, cuan)) {
                    LogUtils.e ("yiEncode00 kong");
                    return "";
                }
                byte[] result = new byte[bytes.length];
                for (int i = 0; i < bytes.length; ++i) {
                    result[i] = (byte) (bytes[i] ^ cuan.charAt (i % cuan.length ()));
                }
                String encodeBASE64 = Base64.encodeToString (result, Base64.NO_WRAP);
                for (int i = 0; i < encodeBASE64.length (); i++) {
                    char charAt = encodeBASE64.charAt (i);
                    if ("/".equals (charAt + "")) {
                        encodeBASE64 = encodeBASE64.replace (charAt + "", "_").trim ();
                    } else if ("+".equals (charAt + "")) {
                        encodeBASE64 = encodeBASE64.replace (charAt + "", "-").trim ();
                    } else if ("=".equals (charAt + "")) {
                        encodeBASE64 = encodeBASE64.replace (charAt + "", "").trim ();
                    }
                }
                endoce = URLEncoder.encode (encodeBASE64, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace ();
            LogUtils.e (LogUtils.getErrorTrace (e));
        }
        return endoce;
    }*/

    /*public static Map<String, String> getJavaParam1SortSign(Map<String, String> map) {

        // pri="Android;S9Zm2+U$-A9D1I>-t4x1MX\"}V_]99?3cJ*sGjY#cJA+rI>N+[AY0fy_M-1}AhZ";
        return getJavaParamAllSortSign (map, PrivateKeyOrConstant.Benefit_Cuan (), 2, "sign");
    }*/

    /**
     * @param map
     * @return
     */
    /*public static Map<String, String> getJavaParamAllSortSign(Map<String, String> map, String pri, int state, String mode) {
        if (map != null) {
        }
        if (StrUtils.IsKong (mode)) {
            mode = "sign";
        }
        return toGetJavaParamSortSign (map, pri, state, mode);
    }*/

    /**
     * 返回一个 带有sign 已经排好序列的map
     *
     * @return
     */
    /*public static Map<String, String> toGetJavaParamSortSign(Map<String, String> ParamMap, String pri,
                                                             int State, String mode) {
        String s = "";
        if (State == 2) {
            String randomNum = RandomNumberUtils.getChar (32);
            String timeStamp = System.currentTimeMillis () + "";
            ParamMap.put ("x-auth-time-stamp", timeStamp);
            ParamMap.put ("x-auth-random-num", randomNum);
            String userAgentHeader = PhoneUuidUtils.getUserAgentInfo ();
            if (StrUtils.IsKong (userAgentHeader)) {
                userAgentHeader = PhoneUuidUtils.createHttpParameterForX7 ();
            }
            ParamMap.put ("user-agent", userAgentHeader);
        }
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>> (ParamMap.entrySet ());
        Collections.sort (list, new Comparator<Map.Entry<String, String>> () {
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                return o1.getKey ().compareTo (o2.getKey ());
            }
        });
        switch (State) {
            case 2:
                try {
                    for (int i = 0; i < list.size (); i++) {
                        Entry<String, String> entry = list.get (i);
                        String key = entry.getKey ();
                        String value = entry.getValue ();
                        if (i != 0) {
                            s += "&" + key + "=" + value;
                        } else {
                            s += key + "=" + value;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                break;
            default:
                break;
        }
        s += pri;
        // "x-auth-mode"不参与验证，所以放到外层
        ParamMap.put ("x-auth-mode", mode);
        ParamMap.put ("x-auth-sign", Md5Utils.encode (s));
        return ParamMap;
    }*/

    /**
     * 设置用户未发布的内容
     *
     * @param tv       控件
     * @param saveFlag 保存的标识
     */
    /*public static void loadUserSaveContent(TextView tv, String saveFlag) {
        //必须是登录的状态下
        String mid = X7UserDataManger.getUserBean ().mid;
        if (StrUtils.allIsNotKong (mid)) {
            String noReplyText = UIUtils.getSharedPreferences ().getString (saveFlag + "@" + mid, "");
            if (allIsNotKong (noReplyText)) {
                if (tv != null) {
                    tv.setText (noReplyText);
                    if (tv instanceof EditText) {
                        EditText et = (EditText) tv;
                        et.setSelection (noReplyText.length ());
                    }
                }
            }
        }
    }*/

    /**
     * 保存用户输入未发布的内容
     *
     * @param saveFlag 保存的标识
     * @param et       输入控件
     */
    /*public static void saveUserEditContent(String saveFlag, EditText et) {
        //必须是登录的状态下
        String mid = X7UserDataManger.getUserBean ().mid;
        if (StrUtils.allIsNotKong (mid)) {
            if (et != null) {
                String text = et.getEditableText ().toString ().trim ();
                if (allIsNotKong (saveFlag)) {
                    UIUtils.getSharedPreferences ().edit ().putString (saveFlag + "@" + mid, text).commit ();
                }
            }
        }
    }*/

    /**
     * 清除用户保存的sp值
     *
     * @param saveFlag 保存的标识
     */
    /*public static void clearUserSaveContent(String saveFlag) {
        //必须是登录的状态下
        String mid = X7UserDataManger.getUserBean ().mid;
        if (StrUtils.allIsNotKong (mid)) {
            String uncommittedComment = UIUtils.getSharedPreferences ().getString (saveFlag + "@" + mid, "");
            if (allIsNotKong (uncommittedComment)) {
                UIUtils.getSharedPreferences ().edit ().putString (saveFlag + "@" + mid, "").commit ();
            }
        }
    }*/

    /**
     * 话题和点评上传图片
     *
     * @param map
     * @return
     */
    /*public static Map<String, String> getTopicPicMap(Map<String, String> map) {
        return getYiParamSortSign (map, PrivateKeyOrConstant.MARKET_NEWCUAN (), 2, 3);
    }

    *//**
     * 判断可变的int 类型的数组是否有值
     *
     * @param intArray
     * @return
     *//*
    public static boolean isIntArrayNotNull(int... intArray) {
        if (intArray == null || intArray.length <= 0) {
            return false;
        }
        return true;
    }

    *//**
     * @param strs
     * @return true:有参数为空
     *//*
    public static boolean isExitEmptyParameter(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (str == null || str.equals ("") || str.equals (" ")
                    || str.equals ("NULL") || str.equals ("null")) {
                return true;
            }
        }
        return false;
    }*/

    /**
     * @param strs
     * @return true:有参数为空
     */
    public static boolean isExitEmptyParameter(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (str == null || str.equals("") || str.equals(" ")
                    || str.equals("NULL") || str.equals("null")) {
                return true;
            }
        }
        return false;
    }
}
