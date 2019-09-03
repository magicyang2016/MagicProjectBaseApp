package com.magic.magicprojectbaseapp.core.http.api;

import com.magic.magicprojectbaseapp.mvp.entity.LoginBean;
import com.magic.magicprojectbaseapp.mvp.entity.StudentBean;

import java.util.Map;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * <pre>
 *     author : xiedi
 *     time   : 2019/06/05
 *     desc   :请求
 * </pre>
 */
public interface MallRequest {
    /**
     * GET请求
     *
     * @param url     api接口url
     * @param request 请求参数map
     * @return
     */
//    @GET
//    Observable<HttpResponse> get(@Url String url, @QueryMap TreeMap<String, Object> request);

    /**
     * 登录
     *
     * @param userName
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST(AllUrl.loginUrl)
    Observable<LoginBean> login(@Field("usercode") String userName, @Field("password") String pwd, @Field("macadd") String device);
    /**
     *  获取服务器地址
     *
     * @return
     */
   /* @FormUrlEncoded
    @POST(AllUrl.readConfInfoUrl)
    Observable<UrlBean> getUrlInfo();*/
    /**
     *  设置服务器地址
     *
     * @return
     */
 /*   @FormUrlEncoded
    @POST(AllUrl.readConfInfoUrl)
    Observable<UrlBean> setUrlInfo();*/
    /**
     *  获取客户列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST(AllUrl.getStudent)
    Observable<StudentBean> getStudent(@FieldMap Map<String, Object> params);

    @Streaming
    @GET()
    Call<ResponseBody> download(@Url String url);

    /**
     *  通话界面获取客户信息
     *
     * @return
     */
   /* @FormUrlEncoded
    @POST(AllUrl.queryCustInfoUrl)
    Observable<CustomEntity> getCustomInfo(@FieldMap Map<String, String> params);
*/
    /**
     *  通话界面提交通话记录
     *
     * @return
     */
   /* @FormUrlEncoded
    @POST(AllUrl.marResultSubmitUrl)
    Observable<UploadResultBean> upLoadPhoneRecord(@FieldMap Map<String, String> params);*/
    /**
     *录音上传
     * @return
     */
    @Multipart
    @POST(AllUrl.upRecordUrl)
    Observable<String> upLoadSoundRecord(@PartMap Map<String, String> params, @Part MultipartBody.Part file);

 /**
     *营销记录查询,通话记录tab中的,营销电话列表
     * @return
     */
   /* @FormUrlEncoded
    @POST(AllUrl.marResultListUrl)
    Observable<YingXDHentity> getSalesPhoneRecordList(@PartMap Map<String, Object> params);
*/


}