package game.map;

import game.map.objects.MapObject;

import java.util.ArrayList;

public class GameMap {
    private ArrayList<ArrayList<MapObject>> mapObjList = new ArrayList<>();

    public GameMap(ArrayList<ArrayList<MapObject>> mapObjList) {
        this.mapObjList = mapObjList;
    }

    public ArrayList<ArrayList<MapObject>> getMapObjList() {
        return mapObjList;
    }

    public GameMap setMapObjList(ArrayList<ArrayList<MapObject>> mapObjList) {
        this.mapObjList = mapObjList;
        return this;
    }

    public void addWithCordinatesObject(int cordRow, int cordColumn, MapObject mapObject) {
        for (int i =0;i<mapObjList.size();i++){
            if (i == cordRow){
                ArrayList<MapObject> row = mapObjList.get(i);
                for(int j=0;j<row.size();j++) {
                    if(j==cordColumn){
                        mapObjList.get(cordRow).add(cordColumn,mapObject);
                    }
                }
            }
        }
    }

    public MapObject getObjectByCordinates(int cordRow, int cordColumn){
        for (int i =0;i<mapObjList.size();i++){
            if (i == cordRow){
                ArrayList<MapObject> row = mapObjList.get(i);
                for(int j=0;j<row.size();j++) {
                    if(j==cordColumn){
                        return mapObjList.get(cordRow).get(cordColumn);
                    }
                }
            }
        }
        return null;
    }


}
