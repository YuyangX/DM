#changement eventuels:
1,将所有Stirng{} 改成HASHMAP《Stirng，String》
2，将interface controller改成abstract class。（待定
3, 在controller中实现isvalid，然后各个子controller继承该方法。
第二种方案，将isvalid的功能移植到menu中去。
4，给所有类添加serializable，以及public static final long serialVersionUID。
5. 我们采取了第二种方案。所以，之前在controleur中需要返回int的方法可以改成返回bool，如cherchercompte。需要返回bool的可以改成返回void。 
6. 注意，我们需要可以直接从formulaire跳到RDV！因为如果第一次预约填完表就可能立刻prendre第二个RdV（在IO中处理）。
7. 关于profile de vaccination 与 formulaire。formulaire 不会被储存在系统中。它存在的一切意义是现实纸面上的 ： 被打印，被手动填。当一天结束之后，employe会根据formulaire中 的信息完善profile de vaccination， 至于被打印formulaire最后会如何，由于文中提到需要employe和professionnel签字，我想他们一定会被放在纸质档案库妥善保管的。
8. 潜在的问题 ： Date！！！！！！！！ compte里 date de naissance是String，而profile里是Date，离谱了。yijing jiang string gaiwei date le.
9. add a constructor of Class date, which accepts a string as a argument.因此也需要修改plagehoraire。
10. 兄弟们不要忘记填表格之前可以通过生日和邮箱找回numero de compte
11. retrouverCompte return type String -> Boolean
12. creerCompte 由于numero de compte 自动生成，所以，需要将返回值改为String以告知employe与visiteur,方便接下来formulaire的填写。
13. 填写formulaire分为两种情况，在IO中也应注意这个问题。第一种是没有账户的，创建账户后直接跳到Formulaire填写的界面，不需要再输入numero de compte。另一种是已经有账户的，这样就需要先输入账户号，然后调用cherchercompte找到，再跳到填写的界面。（注意，formuaire


!!!!!!!!!!!!!!!!!! 14 最终未采用，采用了15

14. 13可以。但是，我们发现需要修改creer un compte，使他也可以将compteAmodifier修改为刚刚创建的这个compte，同时还需要修改repertoire里的加入compte的methode，让他加入的时候将index修改为最后一个，也就是刚刚加入的那个。否则填表的时候不知道填的是哪个。



15. 14的一个alternative是，io中返回string后，直接用chercherCompte找，然后把getcompte改成从最后找起，哈哈哈哈哈哈，这样代价就是o（1）了！最终采用了这种。
16. 删除所有枚举类。注意，profiledevaccination的第一针第二针问题。由于在formulaire中问题是你是否接种了第一针。我们的infoscanner（后来改名成info2profile）必须和info2formulaire中的key recu1Dose一样，所以 non -》 第一针， oui-》第二针。那么这样，一天结束时employecompleter的时候我们就会问他是否是 等等。不用了。当我没说！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！1
17. 关于一些接连在一起的动作，在IO里需要有必要的说明。


compte 部分可能的IO设计。:

1.creer un compte et remplir un formulaire tout de suit
    ####注释 ： 如果employe选了1,那么menu会显示 ：
    
    please enter messages below. 
    You can press 6 to exit or continue to
    remplir un formulaire:
    1,nom 
    2,prenom
    3,
    4,
    5,...
    6,exit
    ###注释：这时employe就会依次（或者不依次）选择1，2,3,4,5来输入创建compte所需的信息。
    输入完之后按6退出（或者按照熊哥的设想，强制输入顺序，自动退出或进行下一步。）比如
    当他输入 1 并按回车后，menu需要显示 ： 
        veuillez entrer un nom （contrainte de format 24 caractere max）：
    |所
    |有
    |都
    |输
    |完
    |之
    |后
    V     menu会显示
    votre numero de compte ： xxxxxxxxxxxx
    Remplir le questionnaire（formulaire）
       1.
       2.
       3.。。。
     ## 注释 ： 如果不将creercompte和 remplir连起来，也可以直接返回1.creercompte2modifiercompte的主菜单，再选modifier compte来填表。

2.modifier un compte
    if 2 is selected, menu will print : 

    1. entrer num de compte
    2. retrouver numero de compte
        |
        |     输入numero de compte 后显示 ： 
        |
        V
    1. remplir formulaire
    2. modifier nom
    3. modifier prenom
    4. modifier tele
    5. completer profile
3.supprimer un compte
4.exit

