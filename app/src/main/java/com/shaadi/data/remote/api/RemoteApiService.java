package com.shaadi.data.remote.api;

import com.shaadi.data.remote.base.RemoteContract;
import com.shaadi.models.ApiResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rohit on 31/01/19.
 */
public interface RemoteApiService {
    /*
    {"results":[{"gender":"male","name":{"title":"mr","first":"isolino","last":"martins"},"location":{"street":"3370 rua joão xxiii","city":"mogi guaçu","state":"mato grosso do sul","postcode":29859,"coordinates":{"latitude":"-75.8480","longitude":"-166.1041"},"timezone":{"offset":"+6:00","description":"Almaty, Dhaka, Colombo"}},"email":"isolino.martins@example.com","login":{"uuid":"88c55863-eb67-4858-975c-7a43951fa16c","username":"goldenpeacock527","password":"hollywoo","salt":"4TzEIvHt","md5":"7576added153b8f12d114df4aff73f2a","sha1":"aff61cd16555cf1263ce6217252eb7a50870579b","sha256":"c47bbcbfb3fc799cafc1471a0a09f73936546ce70eb597cdc114977dc57a7e9f"},"dob":{"date":"1979-09-04T05:56:32Z","age":39},"registered":{"date":"2007-03-27T04:52:46Z","age":11},"phone":"(53) 1960-4853","cell":"(93) 8928-4203","id":{"name":"","value":null},"picture":{"large":"https://randomuser.me/api/portraits/men/18.jpg","medium":"https://randomuser.me/api/portraits/med/men/18.jpg","thumbnail":"https://randomuser.me/api/portraits/thumb/men/18.jpg"},"nat":"BR"}],"info":{"seed":"ce9ce8c4f7b7994c","results":1,"page":1,"version":"1.2"}}
     */
    @GET(RemoteContract.API)
    Observable<ApiResponseModel> requestForApi(@Query("results") int results);
}
