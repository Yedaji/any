package com.dj.niuke;

/**
 * @author ydj
 * @date 2024/10/08 14:07
 **/
public class test {
    public static void main(String[] args) {
        // 张三
        int zhangsanPay = Menu.PACKAGE_1.getPrice() + Menu.BEEF_PATTIES.getPrice();
        int lisiPay = Menu.MID_INTESTINE_NOODLES.getPrice() + Menu.MILK_TEA.getPrice() * 2;

        System.out.println("张三需支付：" + zhangsanPay);
        System.out.println("李四需支付：" + lisiPay);
    }

    public enum Menu {
        BIG_BEEF_NOODLES("大碗牛肉面", 18),
        MID_BEEF_NOODLES("中碗牛肉面", 16),
        SMALL_BEEF_NOODLES("小碗牛肉面", 14),
        BIG_INTESTINE_NOODLES("大碗肥肠面", 20),
        MID_INTESTINE_NOODLES("中碗肥肠面", 18),
        SMALL_INTESTINE_NOODLES("小碗肥肠面", 16),
        BEEF_PATTIES("牛肉饼", 10),
        MILK_TEA("奶茶", 12),
        PACKAGE_1("套餐1（1大碗牛肉面+1个牛肉饼+1杯奶茶", 38),
        PACKAGE_2("套餐2（1大碗肥肠面+1个牛肉饼+1杯奶茶", 40);
        private String name;
        private Integer price;
        Menu(String name, int price) {
            this.name = name;
            this.price = price;
        }
        public Integer getPrice() {
            return price;
        }
        public String getName() {
            return name;
        }
    }
}
