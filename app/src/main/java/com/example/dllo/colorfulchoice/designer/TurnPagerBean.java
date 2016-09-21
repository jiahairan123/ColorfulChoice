package com.example.dllo.colorfulchoice.designer;

import java.util.List;

/**
 * Coder: JiaHaiRan
 * created on 16/9/21 21:02
 */

public class TurnPagerBean {

    /**
     * follow_num : 88
     * city : 埃因霍温
     * concept : 设计创意的本质上是对我们的行为和生活方式的反思
     * article_num : 0
     * name : Marko Macura & Ingeborg van Uden
     * product_num : 4
     * label : Studio Macura 联合创始人
     * introduce_images : ["http://dstatic.zuimeia.com/common/image/2016/9/21/cba39fd4-6a53-412f-abdb-21da4a8ec3ff_800x800.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/20/12747799-dcfe-45c0-98ee-941612d17ab7_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/21/fbbf32f8-dda3-45d8-9957-05d4f6b74cc3_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/21/fa47585d-595e-43a9-a821-f4ea031897f1_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/21/5c4d8f0d-c9b1-4a67-b30d-c4f5cebd88dc_800x800.jpeg"]
     * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/9/21/fea75b4d-60cf-4d2c-9106-02dbe186952a.jpg
     * is_followed : 0
     * id : 120
     * categories : [{"id":12,"name":"极简"},{"id":27,"name":"家居"},{"id":30,"name":"独立设计师"},{"id":53,"name":"埃因霍温"}]
     * description : Ingeborg 和 Macura 2010 年共同创立了家居配饰设计品牌Studio Macura. 他们有各自不同的专业背景：平面设计和产品设计。区别于公式化的设计风格，他们从叙事和文化的角度去进行创造。Studio Macura的工作室在荷兰的埃因霍温，那里汇集了一大批欧洲的手工匠人和传统制造商们。
     */

    private DataBean data;
    /**
     * data : {"follow_num":88,"city":"埃因霍温","concept":"设计创意的本质上是对我们的行为和生活方式的反思","article_num":0,"name":"Marko Macura & Ingeborg van Uden","product_num":4,"label":"Studio Macura 联合创始人","introduce_images":["http://dstatic.zuimeia.com/common/image/2016/9/21/cba39fd4-6a53-412f-abdb-21da4a8ec3ff_800x800.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/20/12747799-dcfe-45c0-98ee-941612d17ab7_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/21/fbbf32f8-dda3-45d8-9957-05d4f6b74cc3_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/21/fa47585d-595e-43a9-a821-f4ea031897f1_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/21/5c4d8f0d-c9b1-4a67-b30d-c4f5cebd88dc_800x800.jpeg"],"avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/9/21/fea75b4d-60cf-4d2c-9106-02dbe186952a.jpg","is_followed":0,"id":120,"categories":[{"id":12,"name":"极简"},{"id":27,"name":"家居"},{"id":30,"name":"独立设计师"},{"id":53,"name":"埃因霍温"}],"description":"Ingeborg 和 Macura 2010 年共同创立了家居配饰设计品牌Studio Macura. 他们有各自不同的专业背景：平面设计和产品设计。区别于公式化的设计风格，他们从叙事和文化的角度去进行创造。Studio Macura的工作室在荷兰的埃因霍温，那里汇集了一大批欧洲的手工匠人和传统制造商们。"}
     * result : 1
     */

    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private int follow_num;
        private String city;
        private String concept;
        private int article_num;
        private String name;
        private int product_num;
        private String label;
        private String avatar_url;
        private int is_followed;
        private int id;
        private String description;
        private List<String> introduce_images;
        /**
         * id : 12
         * name : 极简
         */

        private List<CategoriesBean> categories;

        public int getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(int follow_num) {
            this.follow_num = follow_num;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConcept() {
            return concept;
        }

        public void setConcept(String concept) {
            this.concept = concept;
        }

        public int getArticle_num() {
            return article_num;
        }

        public void setArticle_num(int article_num) {
            this.article_num = article_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProduct_num() {
            return product_num;
        }

        public void setProduct_num(int product_num) {
            this.product_num = product_num;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(int is_followed) {
            this.is_followed = is_followed;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getIntroduce_images() {
            return introduce_images;
        }

        public void setIntroduce_images(List<String> introduce_images) {
            this.introduce_images = introduce_images;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
