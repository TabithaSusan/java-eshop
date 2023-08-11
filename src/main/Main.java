package main;

import business.*;
import persistence.*;
import presentation.Overview;

import java.sql.Connection;


import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Connection conn = Connect.connect();
        ILoginPersistence loginPersistence = new LoginPersistence(conn); // Create your persistence object
        IArticlePersistence articlePersistence = new ArticlePersistence(conn);
        IRegistPersistence registPersistence = new RegistPersistence(conn);
        ILoginBusiness loginBusiness = new LoginBusiness(loginPersistence); // Create your business object, pass 'loginPersistence' to it
        IArticleBusiness articleBusiness = new ArticleBusiness(articlePersistence);
        IRegistBusiness registBusiness = new RegistBusiness(registPersistence);
        Overview overview = new Overview(loginBusiness, articleBusiness, registBusiness);
        overview.setVisible(true);

        System.out.println(articleBusiness.getArticleName(4)); //0 index
    }
}


