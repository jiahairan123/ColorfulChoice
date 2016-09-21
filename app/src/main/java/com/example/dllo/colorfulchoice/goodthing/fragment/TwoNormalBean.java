package com.example.dllo.colorfulchoice.goodthing.fragment;

import java.util.List;

/**
 * Created by mayinling on 16/9/21.
 */
public class TwoNormalBean {

    private DataBean data;

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
        private int unlike_user_num;
        private DesignerBean designer;
        private String name;
        private int comment_num;
        private double price;
        private int is_marked;
        private int like_type;
        private int like_user_num;
        private int mark_user_num;
        private long publish_at;
        private int id;
        private String digest;
        private String desc;
        private List<String> cover_images;

        private List<ReferProductsBean> refer_products;
        private List<?> comments;


        private List<ShopUrlsBean> shop_urls;
        private List<?> refer_articles;
        private List<String> images;

        public int getUnlike_user_num() {
            return unlike_user_num;
        }

        public void setUnlike_user_num(int unlike_user_num) {
            this.unlike_user_num = unlike_user_num;
        }

        public DesignerBean getDesigner() {
            return designer;
        }

        public void setDesigner(DesignerBean designer) {
            this.designer = designer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getIs_marked() {
            return is_marked;
        }

        public void setIs_marked(int is_marked) {
            this.is_marked = is_marked;
        }

        public int getLike_type() {
            return like_type;
        }

        public void setLike_type(int like_type) {
            this.like_type = like_type;
        }

        public int getLike_user_num() {
            return like_user_num;
        }

        public void setLike_user_num(int like_user_num) {
            this.like_user_num = like_user_num;
        }

        public int getMark_user_num() {
            return mark_user_num;
        }

        public void setMark_user_num(int mark_user_num) {
            this.mark_user_num = mark_user_num;
        }

        public long getPublish_at() {
            return publish_at;
        }

        public void setPublish_at(long publish_at) {
            this.publish_at = publish_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getCover_images() {
            return cover_images;
        }

        public void setCover_images(List<String> cover_images) {
            this.cover_images = cover_images;
        }

        public List<ReferProductsBean> getRefer_products() {
            return refer_products;
        }

        public void setRefer_products(List<ReferProductsBean> refer_products) {
            this.refer_products = refer_products;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public List<ShopUrlsBean> getShop_urls() {
            return shop_urls;
        }

        public void setShop_urls(List<ShopUrlsBean> shop_urls) {
            this.shop_urls = shop_urls;
        }

        public List<?> getRefer_articles() {
            return refer_articles;
        }

        public void setRefer_articles(List<?> refer_articles) {
            this.refer_articles = refer_articles;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class DesignerBean {
            private int follow_num;
            private String city;
            private String concept;
            private String name;
            private String label;
            private String avatar_url;
            private int is_followed;
            private int id;
            private String description;
            /**
             * id : 31
             * name : 大牌设计师
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

        public static class ReferProductsBean {
            private String name;
            private double price;
            private int mark_user_num;
            private long publish_at;
            private int id;
            private List<String> cover_images;
            private List<String> images;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getMark_user_num() {
                return mark_user_num;
            }

            public void setMark_user_num(int mark_user_num) {
                this.mark_user_num = mark_user_num;
            }

            public long getPublish_at() {
                return publish_at;
            }

            public void setPublish_at(long publish_at) {
                this.publish_at = publish_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<String> getCover_images() {
                return cover_images;
            }

            public void setCover_images(List<String> cover_images) {
                this.cover_images = cover_images;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class ShopUrlsBean {
            private String shop_url;
            private String shop_name;

            public String getShop_url() {
                return shop_url;
            }

            public void setShop_url(String shop_url) {
                this.shop_url = shop_url;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }
        }
    }
}
