package com.example.dllo.colorfulchoice.designer;

import java.util.List;

/**
 * Coder: JiaHaiRan
 * created on 16/9/18 11:46
 */

public class DesignerBean {

    /**
     * has_next : 1
     * designers : [{"city":"纽约","concept":"时尚是有趣味性的，不要拘泥于陈旧。","name":"Jeremy Scott","label":"Moschino 技术总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/013ec08f-e6c8-406e-a178-36718ada9211.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/28/7cb468cf-a97c-4129-a92a-dcb44faa67e1_1000x625.jpeg"],"id":102,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"马尔凯","concept":"我想做经典、时髦、充满趣味的设计","name":"Alessandra Facchinetti","label":"Tod's 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/25/28a4db58-5eb1-4bbe-be83-0041ddaee228.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/0d9b4d80-ecbc-488e-9260-c9ea4c267e00_1000x625.jpeg"],"id":99,"categories":[{"id":31,"name":"大牌设计师"},{"id":37,"name":"马尔凯"}]},{"city":"巴黎","concept":"我的字典里没有厌倦二字","name":"Christian Louboutin","label":"Christian Louboutin 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/dd5abf79-77bd-40da-9d14-80117a1d1a9c.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/a60bda5d-3939-4690-9bfb-43b43f2efa65_1000x625.jpeg"],"id":94,"categories":[{"id":31,"name":"大牌设计师"},{"id":39,"name":"巴黎"}]},{"city":"纽约","concept":"请和衣服愉快的玩耍，Kenzo就应该有趣好玩。","name":"Carol Lim & Humberto Leon","label":"Kenzo 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/22/3a68f9a2-5299-4041-8d08-9c1666df7477.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/23/736d93df-8ac3-457a-8780-7e6063581376_1000x625.jpeg"],"id":92,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"纽约","concept":"高品质是无法复制的。好的声望需要很久才能建立。","name":"Jenna Lyons","label":"J. Crew 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/4a45ba58-6d4f-469d-beba-92fea5e3536d.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/2c36e784-dce1-4dca-8c3f-5b0855cd087b_1000x625.jpeg"],"id":93,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"20岁是用来玩的，30岁要开始做事儿，40岁你就要站在正确的队列上了","name":"Dean & Dan Caten","label":"Dsquared2 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/fbe58fc4-b9eb-4f96-b8eb-de362017aecf.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/a45bb8cc-a390-483d-8586-fea5e18b0a74_1000x625.jpeg"],"id":96,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"马德里","concept":"自由才是最大的奢侈","name":"Manolo Blahnik","label":"Manolo Blahnik 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/a74a4ea2-a67e-4329-bc6b-ccd9447e02a8.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/f11e4d6c-25a8-455f-b728-90edb43e1e68_1000x625.jpeg"],"id":112,"categories":[{"id":31,"name":"大牌设计师"},{"id":32,"name":"马德里"}]},{"city":"伦敦","concept":"我的设计必须是你能穿在脚上的东西，并且要让你感觉自信、性感与舒适","name":"Paul Andrew","label":"Paul Andrew 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/6e343bb0-6c77-455d-bddf-f76fa434f3d3.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/f9766a79-9c9b-4ff7-a9e7-790456c33c4b_1000x625.jpeg"],"id":111,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"我最需要的就是不知疲倦的工作精神，加上骨子里渴望创造的强烈欲望","name":"Tom Ford ","label":"Tom Ford 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/40dc05c6-d86c-4e26-a55b-7360528860ad.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/2cc130d1-6cf4-4618-b0ca-64eeaa384177_1000x625.jpeg"],"id":110,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"罗马","concept":"每季你都可以讲不同的故事，但只能是由同一语言讲出","name":"Maria Grazia Chiuri & Pierpaolo Piccioli","label":"Valentino 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/70986e1d-c00b-460b-8b6b-39ca5162be81.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/9044ed94-788b-40e2-a709-d8b50b82de88_1000x625.jpeg"],"id":109,"categories":[{"id":31,"name":"大牌设计师"},{"id":33,"name":"罗马"}]},{"city":"马德里","concept":"女人就像艺术品一样，没有年龄之分","name":"Josep Font","label":"Delpozo 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/0ad4c457-8223-4f60-b1d9-53894d62277e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/dfbb26a5-df4c-4acd-a989-d0e89867576e_1000x625.jpeg"],"id":108,"categories":[{"id":31,"name":"大牌设计师"},{"id":32,"name":"马德里"}]},{"city":"斯德哥尔摩","concept":"激进有时也是一种牺牲","name":"Jonny Johansson","label":"Acne Studios 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/d89e4d31-2098-475a-84c7-dfc94eddb2c5.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/70071b5c-9986-4fc4-ab3f-c6c081e20f2a_1000x625.jpeg"],"id":106,"categories":[{"id":31,"name":"大牌设计师"},{"id":34,"name":"斯德哥尔摩"}]},{"city":"米兰","concept":"鞋子不该成为装饰的焦点","name":"Gianvito Rossi","label":"Gianvito Rossi 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/0d8b88b1-19bf-40b3-8d49-a0b677899857.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/d43f68a0-b3b8-4057-b376-1e756ecd4169_1000x625.jpeg"],"id":104,"categories":[{"id":31,"name":"大牌设计师"},{"id":35,"name":"米兰"}]},{"city":"圣加仑州","concept":"时尚应该简单些，这个时代已经够复杂了","name":"Albert Kriemler","label":"Akris 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/76ca8ba0-8dd8-49e6-9b92-83396001b1ff.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/d11e6af0-cef1-47a7-84ee-c9f25af8aefb_1000x625.jpeg"],"id":103,"categories":[{"id":31,"name":"大牌设计师"},{"id":36,"name":"圣加仑州"}]},{"city":"米兰","concept":"我希望人们记住Dolce & Gabbana 是因为感官的享受，地中海气质以及我们对生活的诚意","name":"Domenico Dolce & Stefano Gabbana","label":"Dolce & Gabbana 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/25/935baeae-2daa-435c-8b49-c7d18c2f37a1.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/d8a75820-2ecd-427c-b925-4645f00814cb_1000x625.jpeg"],"id":97,"categories":[{"id":31,"name":"大牌设计师"},{"id":35,"name":"米兰"}]},{"city":"米兰","concept":"时尚就是做梦以及让其他人也一起做梦","name":"Donatella Versace","label":"Versace 首席设计师，董事会副主席","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/33bdb955-87cf-4744-9c72-5e0fb099a2d9.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/50b2a3c3-9970-444f-80f3-f268c16b9978_1000x625.jpeg"],"id":95,"categories":[{"id":31,"name":"大牌设计师"},{"id":35,"name":"米兰"}]},{"city":"伦敦","concept":"当你的品牌能够被他人预测的时候，你就遇上大麻烦了","name":"Jonathan Anderson","label":"J.W.Anderson 创始人，Loewe 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/12/1c550e7b-56cf-44a2-af40-7bfe07f0827b.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/12/89aec0a9-48a7-4858-95d4-dac3b752b94d_1000x625.jpeg"],"id":82,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"Charlotte Olympia的品牌灵魂来源于我对40年代好莱坞魅力的怀念","name":"Charlotte Dellal","label":"Charlotte Olympia 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/12/130137b2-cd64-4384-8b13-c4ce16ce4147.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/12/5f936c34-1019-4a28-96f4-d0b11eed49ca_1000x625.jpeg"],"id":73,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"纽约","concept":"为有个性的人而设计","name":"Karen Walker","label":"Karen Walker 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/1/32071d69-bd7e-4c40-a02f-76fa6ea92a4c.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/1/5ca179f9-4dda-4df4-89c1-164f8e4efb98_1000x625.jpeg"],"id":42,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"我热衷于在怪诞中寻找美","name":"Alexander McQueen","label":"Alexander McQueen 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/22/c461e48f-38d6-4afc-b0d9-2b75a3af78c2.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/22/98223637-3145-4ca8-a6fe-6f459d3ffe9a_1000x625.jpeg"],"id":38,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"巴黎","concept":"在 Céline 让我有了一个表达的好机会，讲述我对于女性应该如何认识自我，以及如何被外部世界理解的观念","name":"Phoebe Philo","label":"Céline 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/27/2e5cb533-44ba-458b-90ca-a8070d9cd679.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/27/1a703b97-e022-4476-9c2e-36b0dda40005_1000x625.jpeg"],"id":37,"categories":[{"id":31,"name":"大牌设计师"},{"id":39,"name":"巴黎"}]},{"city":"纽约","concept":"当你跌倒\u2014\u2014如果你想要学着做一个花滑选手\u2014\u2014你需要让自己跃起，重新开始。不要让任何事情阻止你","name":"Vera Wang","label":"前 Vogue 高级时尚编辑，高档婚纱品牌 Vera Wang 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/8/b6fd4438-28a7-415b-ba2b-f0ef77a429b5.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/28/972d7094-09c7-48c5-bcde-935a15663131_1000x625.jpeg"],"id":26,"categories":[{"id":24,"name":"西雅图"},{"id":31,"name":"大牌设计师"}]},{"city":"纽约","concept":"我的作品与盛开的牡丹很像","name":"Thakoon Panichgul","label":"Thakoon Panichgul 女装创始人 ，TASAKI 珠宝设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/26/raw_545a81e438fe385cd93347482453b3fa51190183_c1d27acd-06ad-431_jS7Pv4I.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/1/3a89c190-dcc1-443e-8134-695058fc0e6c_1142x714.jpeg"],"id":15,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"巴黎","concept":"你可以让Chloé gir离开巴黎，但是你无法让巴黎舍弃Chloé gir","name":"Clare Waight Keller","label":"Chloé 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/25/213_4b758f42-f0af-43e0-b276-eea35f6a1d07.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/28/aecaa713-3d1f-491e-8085-26310d0d65d7_1000x625.jpeg"],"id":13,"categories":[{"id":31,"name":"大牌设计师"},{"id":39,"name":"巴黎"}]},{"city":"蒙特利尔","concept":"迷恋乔布斯的极简主义，所以启动匹配的包履设计","name":"Byron & Dexter Peart","label":"Want Les Essentiels 联合创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/27/9fd5632d-d8c0-4872-a383-9ac701020623.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/15/3628c163-5cfd-4186-a673-caa4c117be0b_1194x746.jpeg"],"id":12,"categories":[{"id":31,"name":"大牌设计师"},{"id":50,"name":"蒙特利尔"}]},{"city":"纽约","concept":"对于很多人而言，假如生活远离时尚会失去意义；而对于我而言，正好相反","name":"Marc Jacobs","label":"Marc Jacobs 品牌联合创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/31/e42e9549-f4a2-4233-9887-bb30348b280e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/5/31/d9ccbab2-ca1f-4b97-be03-a31ed57dbb15_1200x750.jpeg"],"id":10,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"东京","concept":"我的能量来源于自由和反抗","name":"Rei Kawakubo","label":"Comme des Gargons 品牌创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/27/b376cbb7-c134-4a09-9069-46c52633ada5.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/7/8/d489d3d0-fce2-4d3a-a9ad-af0244e92a95_1000x625.jpeg"],"id":9,"categories":[{"id":31,"name":"大牌设计师"},{"id":51,"name":"东京"}]},{"city":"纽约","concept":"于无序中制造美感","name":"Floriana Gavriel & Rachel Mansur","label":"Mansur Gavriel 设计师组合","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/27/2324d7c2-849c-4d62-ab44-a555f21e929e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/7/8/2d9d0977-9670-43ec-8e68-b9204b500d4f_1000x625.jpeg"],"id":8,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"东京","concept":"作为一个时装设计师或者艺术家，你必须要愤怒","name":"Yohji Yamamoto","label":"Yohji Yamamoto 品牌创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/31/37096640-5c4b-4bf6-873a-16d2a159ce0e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/5/26/shanben11_f47f593c-4481-417f-9931-fe6aefbb398c_900x562.jpeg"],"id":7,"categories":[{"id":31,"name":"大牌设计师"},{"id":51,"name":"东京"}]},{"city":"伦敦","concept":"我是朋克的救世主，想看看一个人能在多大程度上去改变这个世界","name":"Vivienne Westwood","label":"Vivienne Westwood 品牌创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/30/f3f5a9d9-773c-46f5-a9f5-274ee0aec528.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/15/4cfc2a18-fd65-4339-9a31-ab17df402476_1200x750.jpeg"],"id":6,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]}]
     */

    private DataBean data;
    /**
     * data : {"has_next":1,"designers":[{"city":"纽约","concept":"时尚是有趣味性的，不要拘泥于陈旧。","name":"Jeremy Scott","label":"Moschino 技术总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/013ec08f-e6c8-406e-a178-36718ada9211.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/28/7cb468cf-a97c-4129-a92a-dcb44faa67e1_1000x625.jpeg"],"id":102,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"马尔凯","concept":"我想做经典、时髦、充满趣味的设计","name":"Alessandra Facchinetti","label":"Tod's 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/25/28a4db58-5eb1-4bbe-be83-0041ddaee228.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/0d9b4d80-ecbc-488e-9260-c9ea4c267e00_1000x625.jpeg"],"id":99,"categories":[{"id":31,"name":"大牌设计师"},{"id":37,"name":"马尔凯"}]},{"city":"巴黎","concept":"我的字典里没有厌倦二字","name":"Christian Louboutin","label":"Christian Louboutin 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/dd5abf79-77bd-40da-9d14-80117a1d1a9c.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/a60bda5d-3939-4690-9bfb-43b43f2efa65_1000x625.jpeg"],"id":94,"categories":[{"id":31,"name":"大牌设计师"},{"id":39,"name":"巴黎"}]},{"city":"纽约","concept":"请和衣服愉快的玩耍，Kenzo就应该有趣好玩。","name":"Carol Lim & Humberto Leon","label":"Kenzo 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/22/3a68f9a2-5299-4041-8d08-9c1666df7477.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/23/736d93df-8ac3-457a-8780-7e6063581376_1000x625.jpeg"],"id":92,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"纽约","concept":"高品质是无法复制的。好的声望需要很久才能建立。","name":"Jenna Lyons","label":"J. Crew 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/4a45ba58-6d4f-469d-beba-92fea5e3536d.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/2c36e784-dce1-4dca-8c3f-5b0855cd087b_1000x625.jpeg"],"id":93,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"20岁是用来玩的，30岁要开始做事儿，40岁你就要站在正确的队列上了","name":"Dean & Dan Caten","label":"Dsquared2 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/fbe58fc4-b9eb-4f96-b8eb-de362017aecf.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/a45bb8cc-a390-483d-8586-fea5e18b0a74_1000x625.jpeg"],"id":96,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"马德里","concept":"自由才是最大的奢侈","name":"Manolo Blahnik","label":"Manolo Blahnik 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/a74a4ea2-a67e-4329-bc6b-ccd9447e02a8.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/f11e4d6c-25a8-455f-b728-90edb43e1e68_1000x625.jpeg"],"id":112,"categories":[{"id":31,"name":"大牌设计师"},{"id":32,"name":"马德里"}]},{"city":"伦敦","concept":"我的设计必须是你能穿在脚上的东西，并且要让你感觉自信、性感与舒适","name":"Paul Andrew","label":"Paul Andrew 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/6e343bb0-6c77-455d-bddf-f76fa434f3d3.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/f9766a79-9c9b-4ff7-a9e7-790456c33c4b_1000x625.jpeg"],"id":111,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"我最需要的就是不知疲倦的工作精神，加上骨子里渴望创造的强烈欲望","name":"Tom Ford ","label":"Tom Ford 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/40dc05c6-d86c-4e26-a55b-7360528860ad.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/2cc130d1-6cf4-4618-b0ca-64eeaa384177_1000x625.jpeg"],"id":110,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"罗马","concept":"每季你都可以讲不同的故事，但只能是由同一语言讲出","name":"Maria Grazia Chiuri & Pierpaolo Piccioli","label":"Valentino 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/70986e1d-c00b-460b-8b6b-39ca5162be81.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/9044ed94-788b-40e2-a709-d8b50b82de88_1000x625.jpeg"],"id":109,"categories":[{"id":31,"name":"大牌设计师"},{"id":33,"name":"罗马"}]},{"city":"马德里","concept":"女人就像艺术品一样，没有年龄之分","name":"Josep Font","label":"Delpozo 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/0ad4c457-8223-4f60-b1d9-53894d62277e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/dfbb26a5-df4c-4acd-a989-d0e89867576e_1000x625.jpeg"],"id":108,"categories":[{"id":31,"name":"大牌设计师"},{"id":32,"name":"马德里"}]},{"city":"斯德哥尔摩","concept":"激进有时也是一种牺牲","name":"Jonny Johansson","label":"Acne Studios 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/d89e4d31-2098-475a-84c7-dfc94eddb2c5.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/70071b5c-9986-4fc4-ab3f-c6c081e20f2a_1000x625.jpeg"],"id":106,"categories":[{"id":31,"name":"大牌设计师"},{"id":34,"name":"斯德哥尔摩"}]},{"city":"米兰","concept":"鞋子不该成为装饰的焦点","name":"Gianvito Rossi","label":"Gianvito Rossi 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/0d8b88b1-19bf-40b3-8d49-a0b677899857.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/d43f68a0-b3b8-4057-b376-1e756ecd4169_1000x625.jpeg"],"id":104,"categories":[{"id":31,"name":"大牌设计师"},{"id":35,"name":"米兰"}]},{"city":"圣加仑州","concept":"时尚应该简单些，这个时代已经够复杂了","name":"Albert Kriemler","label":"Akris 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/27/76ca8ba0-8dd8-49e6-9b92-83396001b1ff.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/29/d11e6af0-cef1-47a7-84ee-c9f25af8aefb_1000x625.jpeg"],"id":103,"categories":[{"id":31,"name":"大牌设计师"},{"id":36,"name":"圣加仑州"}]},{"city":"米兰","concept":"我希望人们记住Dolce & Gabbana 是因为感官的享受，地中海气质以及我们对生活的诚意","name":"Domenico Dolce & Stefano Gabbana","label":"Dolce & Gabbana 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/25/935baeae-2daa-435c-8b49-c7d18c2f37a1.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/27/d8a75820-2ecd-427c-b925-4645f00814cb_1000x625.jpeg"],"id":97,"categories":[{"id":31,"name":"大牌设计师"},{"id":35,"name":"米兰"}]},{"city":"米兰","concept":"时尚就是做梦以及让其他人也一起做梦","name":"Donatella Versace","label":"Versace 首席设计师，董事会副主席","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/23/33bdb955-87cf-4744-9c72-5e0fb099a2d9.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/25/50b2a3c3-9970-444f-80f3-f268c16b9978_1000x625.jpeg"],"id":95,"categories":[{"id":31,"name":"大牌设计师"},{"id":35,"name":"米兰"}]},{"city":"伦敦","concept":"当你的品牌能够被他人预测的时候，你就遇上大麻烦了","name":"Jonathan Anderson","label":"J.W.Anderson 创始人，Loewe 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/12/1c550e7b-56cf-44a2-af40-7bfe07f0827b.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/12/89aec0a9-48a7-4858-95d4-dac3b752b94d_1000x625.jpeg"],"id":82,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"Charlotte Olympia的品牌灵魂来源于我对40年代好莱坞魅力的怀念","name":"Charlotte Dellal","label":"Charlotte Olympia 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/12/130137b2-cd64-4384-8b13-c4ce16ce4147.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/12/5f936c34-1019-4a28-96f4-d0b11eed49ca_1000x625.jpeg"],"id":73,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"纽约","concept":"为有个性的人而设计","name":"Karen Walker","label":"Karen Walker 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/1/32071d69-bd7e-4c40-a02f-76fa6ea92a4c.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/8/1/5ca179f9-4dda-4df4-89c1-164f8e4efb98_1000x625.jpeg"],"id":42,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"伦敦","concept":"我热衷于在怪诞中寻找美","name":"Alexander McQueen","label":"Alexander McQueen 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/22/c461e48f-38d6-4afc-b0d9-2b75a3af78c2.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/22/98223637-3145-4ca8-a6fe-6f459d3ffe9a_1000x625.jpeg"],"id":38,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]},{"city":"巴黎","concept":"在 Céline 让我有了一个表达的好机会，讲述我对于女性应该如何认识自我，以及如何被外部世界理解的观念","name":"Phoebe Philo","label":"Céline 创意总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/27/2e5cb533-44ba-458b-90ca-a8070d9cd679.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/27/1a703b97-e022-4476-9c2e-36b0dda40005_1000x625.jpeg"],"id":37,"categories":[{"id":31,"name":"大牌设计师"},{"id":39,"name":"巴黎"}]},{"city":"纽约","concept":"当你跌倒\u2014\u2014如果你想要学着做一个花滑选手\u2014\u2014你需要让自己跃起，重新开始。不要让任何事情阻止你","name":"Vera Wang","label":"前 Vogue 高级时尚编辑，高档婚纱品牌 Vera Wang 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/8/b6fd4438-28a7-415b-ba2b-f0ef77a429b5.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/28/972d7094-09c7-48c5-bcde-935a15663131_1000x625.jpeg"],"id":26,"categories":[{"id":24,"name":"西雅图"},{"id":31,"name":"大牌设计师"}]},{"city":"纽约","concept":"我的作品与盛开的牡丹很像","name":"Thakoon Panichgul","label":"Thakoon Panichgul 女装创始人 ，TASAKI 珠宝设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/26/raw_545a81e438fe385cd93347482453b3fa51190183_c1d27acd-06ad-431_jS7Pv4I.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/1/3a89c190-dcc1-443e-8134-695058fc0e6c_1142x714.jpeg"],"id":15,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"巴黎","concept":"你可以让Chloé gir离开巴黎，但是你无法让巴黎舍弃Chloé gir","name":"Clare Waight Keller","label":"Chloé 设计总监","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/25/213_4b758f42-f0af-43e0-b276-eea35f6a1d07.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/28/aecaa713-3d1f-491e-8085-26310d0d65d7_1000x625.jpeg"],"id":13,"categories":[{"id":31,"name":"大牌设计师"},{"id":39,"name":"巴黎"}]},{"city":"蒙特利尔","concept":"迷恋乔布斯的极简主义，所以启动匹配的包履设计","name":"Byron & Dexter Peart","label":"Want Les Essentiels 联合创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/27/9fd5632d-d8c0-4872-a383-9ac701020623.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/15/3628c163-5cfd-4186-a673-caa4c117be0b_1194x746.jpeg"],"id":12,"categories":[{"id":31,"name":"大牌设计师"},{"id":50,"name":"蒙特利尔"}]},{"city":"纽约","concept":"对于很多人而言，假如生活远离时尚会失去意义；而对于我而言，正好相反","name":"Marc Jacobs","label":"Marc Jacobs 品牌联合创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/31/e42e9549-f4a2-4233-9887-bb30348b280e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/5/31/d9ccbab2-ca1f-4b97-be03-a31ed57dbb15_1200x750.jpeg"],"id":10,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"东京","concept":"我的能量来源于自由和反抗","name":"Rei Kawakubo","label":"Comme des Gargons 品牌创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/27/b376cbb7-c134-4a09-9069-46c52633ada5.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/7/8/d489d3d0-fce2-4d3a-a9ad-af0244e92a95_1000x625.jpeg"],"id":9,"categories":[{"id":31,"name":"大牌设计师"},{"id":51,"name":"东京"}]},{"city":"纽约","concept":"于无序中制造美感","name":"Floriana Gavriel & Rachel Mansur","label":"Mansur Gavriel 设计师组合","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/27/2324d7c2-849c-4d62-ab44-a555f21e929e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/7/8/2d9d0977-9670-43ec-8e68-b9204b500d4f_1000x625.jpeg"],"id":8,"categories":[{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]},{"city":"东京","concept":"作为一个时装设计师或者艺术家，你必须要愤怒","name":"Yohji Yamamoto","label":"Yohji Yamamoto 品牌创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/31/37096640-5c4b-4bf6-873a-16d2a159ce0e.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/5/26/shanben11_f47f593c-4481-417f-9931-fe6aefbb398c_900x562.jpeg"],"id":7,"categories":[{"id":31,"name":"大牌设计师"},{"id":51,"name":"东京"}]},{"city":"伦敦","concept":"我是朋克的救世主，想看看一个人能在多大程度上去改变这个世界","name":"Vivienne Westwood","label":"Vivienne Westwood 品牌创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/5/30/f3f5a9d9-773c-46f5-a9f5-274ee0aec528.jpg","recommend_images":["http://dstatic.zuimeia.com/common/image/2016/6/15/4cfc2a18-fd65-4339-9a31-ab17df402476_1200x750.jpeg"],"id":6,"categories":[{"id":28,"name":"伦敦"},{"id":31,"name":"大牌设计师"}]}]}
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
        private int has_next;
        /**
         * city : 纽约
         * concept : 时尚是有趣味性的，不要拘泥于陈旧。
         * name : Jeremy Scott
         * label : Moschino 技术总监
         * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/8/27/013ec08f-e6c8-406e-a178-36718ada9211.jpg
         * recommend_images : ["http://dstatic.zuimeia.com/common/image/2016/8/28/7cb468cf-a97c-4129-a92a-dcb44faa67e1_1000x625.jpeg"]
         * id : 102
         * categories : [{"id":17,"name":"纽约"},{"id":31,"name":"大牌设计师"}]
         */

        private List<DesignersBean> designers;

        public int getHas_next() {
            return has_next;
        }

        public void setHas_next(int has_next) {
            this.has_next = has_next;
        }

        public List<DesignersBean> getDesigners() {
            return designers;
        }

        public void setDesigners(List<DesignersBean> designers) {
            this.designers = designers;
        }

        public static class DesignersBean {
            private String city;
            private String concept;
            private String name;
            private String label;
            private String avatar_url;
            private int id;
            private List<String> recommend_images;
            /**
             * id : 17
             * name : 纽约
             */

            private List<CategoriesBean> categories;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<String> getRecommend_images() {
                return recommend_images;
            }

            public void setRecommend_images(List<String> recommend_images) {
                this.recommend_images = recommend_images;
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
}
