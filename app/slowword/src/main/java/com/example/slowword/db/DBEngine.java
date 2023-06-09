package com.example.slowword.db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.slowword.model.Arcticle;
import com.example.slowword.model.User;
import com.example.slowword.model.Word;

import java.util.List;

public class DBEngine {

    private WordDao WordDao;
    private UserDao userDao;
    private ArcticleDao arcticleDao;
    MyDatabase myDatabase;


    // 创建数据库，以及初始化操作数据库接口
    public DBEngine(Context context){
        myDatabase = MyDatabase.getInstance(context);
        WordDao = myDatabase.getWordDao();
        userDao = myDatabase.getUserDao();
        arcticleDao = myDatabase.getArcticleDao();
    }

    public void insertWord(Word Word){
        new InsertAsyncTask(WordDao).execute(Word);
    }

    public void updateWord(Word Word){
        new UpdateAsyncTask(WordDao).execute(Word);
    }

    public void deleteWord(Word Word){
        new DeleteAsyncTask(WordDao).execute(Word);
    }

    public void deleteAllWord(Word Word){
        new DeleteAllAsyncTask(WordDao).execute();
    }

    public void queryAllWord(Word Word){
        new QueryAllAsyncTask(WordDao).execute();
    }

    // 开启一系列异步操作
    static class InsertAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao WordDao;

        public InsertAsyncTask(WordDao WordDao) {
        }

        @Override
        protected Void doInBackground(Word... Word) {
            WordDao.insertWord(Word);
            return null;
        }
    }

    // 更新
    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao WordDao;

        public UpdateAsyncTask(WordDao WordDao) {
        }

        @Override
        protected Void doInBackground(Word... Word) {
            WordDao.updateWord(Word);
            return null;
        }
    }

    // 条件删除
    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao WordDao;

        public DeleteAsyncTask(WordDao WordDao) {
        }

        @Override
        protected Void doInBackground(Word... Word) { // 有条件删除
            WordDao.deleteWord(Word);
            return null;
        }
    }


    // 全部删除
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao WordDao;

        public DeleteAllAsyncTask(WordDao WordDao) {
        }

        @Override
        protected Void doInBackground(Void... voids) { // 有条件删除
            WordDao.deleteAllWord();
            return null;
        }
    }

    // 查询全部
    static class QueryAllAsyncTask extends AsyncTask<Void, Void, List<Word>> {
        private WordDao WordDao;

        public QueryAllAsyncTask(WordDao WordDao) {
        }

        @Override
        protected List<Word> doInBackground(Void... voids) { // 有条件删除
            List<Word> allWord = WordDao.getAllWord();
            return allWord;
        }
    }

    public void insertUser(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void deleteUser(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUser(User user){
        new DeleteAllUserAsyncTask(userDao).execute();
    }

    public void queryAllUser(User user){
        new QueryAllUserAsyncTask(userDao).execute();
    }

    public void queryUserByUP(User user){
        new QueryUserByUPAsyncTask(userDao).execute();
    }

    // 开启一系列异步操作
    static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
        }

        @Override
        protected Void doInBackground(User... user) {
            userDao.insertUser(user);
            return null;
        }
    }

    // 更新
    static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public UpdateUserAsyncTask(UserDao userDao) {
        }

        @Override
        protected Void doInBackground(User... user) {
            userDao.updateUser(user);
            return null;
        }
    }

    // 条件删除
    static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public DeleteUserAsyncTask(UserDao userDao) {
        }

        @Override
        protected Void doInBackground(User... user) { // 有条件删除
            userDao.deleteUser(user);
            return null;
        }
    }


    // 全部删除
    static class DeleteAllUserAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        public DeleteAllUserAsyncTask(UserDao userDao) {
        }

        @Override
        protected Void doInBackground(Void... voids) { // 有条件删除
            userDao.deleteAllUser();
            return null;
        }
    }

    // 查询全部
    static class QueryAllUserAsyncTask extends AsyncTask<Void, Void, List<User>> {
        private UserDao userDao;

        public QueryAllUserAsyncTask(UserDao userDao) {
        }

        @Override
        protected List<User> doInBackground(Void... voids) { // 有条件删除
            List<User> allUser = userDao.getAllUser();
            return allUser;
        }
    }

    // 根据用户名密码查询
    static class QueryUserByUPAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        public QueryUserByUPAsyncTask(UserDao userDao) {
        }

        @Override
        protected User doInBackground(String... strings) { // 有条件删除
            User user = userDao.getUser(strings[0],strings[1]);
            return user;
        }
    }

    // Arcticle
    public void insertArcticle(Arcticle arcticle){
        new InsertArcticleAsyncTask(arcticleDao).execute(arcticle);
    }

    public void updateArcticle(Arcticle arcticle){
        new UpdateArcticleAsyncTask(arcticleDao).execute(arcticle);
    }

    public void deleteArcticle(Arcticle arcticle){
        new DeleteArcticleAsyncTask(arcticleDao).execute(arcticle);
    }

    public void deleteAllArcticle(Arcticle arcticle){
        new DeleteAllArcticleAsyncTask(arcticleDao).execute();
    }

    public void queryAllArcticle(Arcticle arcticle){
        new QueryAllArcticleAsyncTask(arcticleDao).execute();
    }

    // 开启一系列异步操作
    static class InsertArcticleAsyncTask extends AsyncTask<Arcticle,Void,Void>{
        private ArcticleDao arcticleDao;

        public InsertArcticleAsyncTask(ArcticleDao arcticleDao) {
        }

        @Override
        protected Void doInBackground(Arcticle... arcticle) {
            arcticleDao.insertArcticle(arcticle);
            return null;
        }
    }

    // 更新
    static class UpdateArcticleAsyncTask extends AsyncTask<Arcticle,Void,Void>{
        private ArcticleDao arcticleDao;

        public UpdateArcticleAsyncTask(ArcticleDao arcticleDao) {
        }

        @Override
        protected Void doInBackground(Arcticle... arcticle) {
            arcticleDao.updateArcticle(arcticle);
            return null;
        }
    }

    // 条件删除
    static class DeleteArcticleAsyncTask extends AsyncTask<Arcticle,Void,Void>{
        private ArcticleDao arcticleDao;

        public DeleteArcticleAsyncTask(ArcticleDao arcticleDao) {
        }

        @Override
        protected Void doInBackground(Arcticle... arcticle) { // 有条件删除
            arcticleDao.deleteArcticle(arcticle);
            return null;
        }
    }


    // 全部删除
    static class DeleteAllArcticleAsyncTask extends AsyncTask<Void,Void,Void>{
        private ArcticleDao arcticleDao;

        public DeleteAllArcticleAsyncTask(ArcticleDao arcticleDao) {
        }

        @Override
        protected Void doInBackground(Void... voids) { // 有条件删除
            arcticleDao.deleteAllArcticle();
            return null;
        }
    }

    // 查询全部
    static class QueryAllArcticleAsyncTask extends AsyncTask<Void, Void, List<Arcticle>> {
        private ArcticleDao arcticleDao;

        public QueryAllArcticleAsyncTask(ArcticleDao arcticleDao) {
        }

        @Override
        protected List<Arcticle> doInBackground(Void... voids) { // 有条件删除
            List<Arcticle> allArcticle = arcticleDao.getAllArcticle();
            return allArcticle;
        }
    }

}
