package com.example.dllo.colorfulchoice.designer.bean;

import java.util.List;

/**
 * Coder: JiaHaiRan
 * created on 16/9/21 21:02
 */

public class TurnPagerBean {


    /**
     * follow_num : 386
     * city : 伦敦
     * concept : 我是有个人主见的设计师而不是一味地追逐潮流趋势
     * article_num : 0
     * name : Lulu Guinness
     * product_num : 10
     * label : Lulu Guinness 创始人
     * introduce_images : ["http://dstatic.zuimeia.com/common/image/2016/9/11/f4d403c3-86f0-453c-b0bf-643f6fdf918c_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/40cb5741-af1d-4b44-b86f-5af9a9409292_1080x1080.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/8f9acd16-4b57-423e-ba24-c97c750291d5_1080x1080.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/63752df0-f129-46f6-ac03-fbec581c6592_1080x1080.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/1a25cedf-f01f-480f-be13-fa7b46ef20c0_1000x1000.jpeg"]
     * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/9/7/3ec2eb12-5812-4fc9-9004-103f358dd9c1.jpg
     * is_followed : 0
     * id : 115
     * categories : [{"id":3,"name":"包袋"},{"id":28,"name":"伦敦"},{"id":30,"name":"独立设计师"}]
     * description : Lulu Guinness 在29岁的时候创立了Lulu Guinness，她希望设计出复古如玫瑰般的手提包，后来她的包包已经超越时尚配饰的角色，被称为：“明日的珍宝”。2006年 Lulu 获得 0BE 大奖，2009 年，她荣获了独立手包设计大奖 ICONCLAST。
     */

    private DataBean data;
    /**
     * data : {"follow_num":386,"city":"伦敦","concept":"我是有个人主见的设计师而不是一味地追逐潮流趋势","article_num":0,"name":"Lulu Guinness","product_num":10,"label":"Lulu Guinness 创始人","introduce_images":["http://dstatic.zuimeia.com/common/image/2016/9/11/f4d403c3-86f0-453c-b0bf-643f6fdf918c_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/40cb5741-af1d-4b44-b86f-5af9a9409292_1080x1080.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/8f9acd16-4b57-423e-ba24-c97c750291d5_1080x1080.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/63752df0-f129-46f6-ac03-fbec581c6592_1080x1080.jpeg","http://dstatic.zuimeia.com/common/image/2016/9/11/1a25cedf-f01f-480f-be13-fa7b46ef20c0_1000x1000.jpeg"],"avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/9/7/3ec2eb12-5812-4fc9-9004-103f358dd9c1.jpg","is_followed":0,"id":115,"categories":[{"id":3,"name":"包袋"},{"id":28,"name":"伦敦"},{"id":30,"name":"独立设计师"}],"description":"Lulu Guinness 在29岁的时候创立了Lulu Guinness，她希望设计出复古如玫瑰般的手提包，后来她的包包已经超越时尚配饰的角色，被称为：\u201c明日的珍宝\u201d。2006年 Lulu 获得 0BE 大奖，2009 年，她荣获了独立手包设计大奖 ICONCLAST。"}
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
         * id : 3
         * name : 包袋
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
