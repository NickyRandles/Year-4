package com.example.nicky.listdatabase;

/**
 * Created by nicky on 12/10/2015.
 */
public class List {

    private int _id;
    private String _listName;
    private String _list;

    public List(){
    }

    public List(String listName, String list){
        this._listName = listName;
        this._list = list;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_listName(String _listName) {
        this._listName = _listName;
    }

    public void set_list(String _list) {
        this._list = _list;
    }

    public int get_id() {
        return _id;
    }

    public String get_listName() {
        return _listName;
    }

    public String get_list() {
        return _list;
    }
}
