package com.example.dllo.colorfulchoice.goodthing;

import java.util.List;

/**
 * Created by mayinling on 16/9/18.
 */
public class PopBean {


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

        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            private int id;
            private String name;

            private List<SubCategoriesBean> sub_categories;

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

            public List<SubCategoriesBean> getSub_categories() {
                return sub_categories;
            }

            public void setSub_categories(List<SubCategoriesBean> sub_categories) {
                this.sub_categories = sub_categories;
            }

            public static class SubCategoriesBean {
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
}
