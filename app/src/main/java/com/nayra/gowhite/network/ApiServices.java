package com.nayra.gowhite.network;

import com.nayra.gowhite.model.LoginResponse;
import com.nayra.gowhite.view_model.AddAppointmentViewModel;
import com.nayra.gowhite.view_model.AddAppointmentWithVendorViewModel;
import com.nayra.gowhite.view_model.GetAreasViewModel;
import com.nayra.gowhite.view_model.GetCitiesViewModel;
import com.nayra.gowhite.view_model.GetCountriesViewModel;
import com.nayra.gowhite.view_model.RegisterViewModel;
import com.nayra.gowhite.view_model.SearchByPhoneViewModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by nayrael-sayed on 2/16/18.
 */

public interface ApiServices {

    @FormUrlEncoded
    @POST("token")
    Call<LoginResponse> login(@Field("grant_type") String type, @Field("password") String password, @Field("username") String email);


    @GET("api/Country")
    Call<GetCountriesViewModel> getCountries();


    @GET("api/Cities/{country_id}")
    Call<GetCitiesViewModel> getCities(@Path("country_id") int country_id);

    @GET("api/areas/{city_id}")
    Call<GetAreasViewModel> getAreasByCityId(@Path("city_id") int country_id);

    @GET("api/areas/")
    Call<GetAreasViewModel> getAllAreas();

    @GET("api/Users/Search/{phone_num}")
    Call<SearchByPhoneViewModel> searchByPhone(@Path("phone_num") String phone_num);

    @FormUrlEncoded
    @POST("Register")
    Call<RegisterViewModel> register(@Field("username") String email, @Field("password") String password,
                                     @Field("confirmpassword") String confirmPassword, @Field("email") String email2,
                                     @Field("FirstName") String firstName, @Field("LastName") String lastName,
                                     @Field("CountryID") int countryID, @Field("PhoneNumber") String phone);

    @FormUrlEncoded
    @POST("Appointments")
    Call<AddAppointmentViewModel> addAppointment(@Field("Type") int type, @Field("StartDate") String date,
                                                 @Field("Address") String address, @Field("Duration") int duration,
                                                 @Field("Amount") int amount, @Field("WantCleaningMatrial") int wantCleaningMaterials,
                                                 @Field("CleaningInstructions") String cleaningInstructions, @Field("AreaID") int areaId,
                                                 @Field("CityID") int cityId, @Field("PhoneNumber") String phoneNumber);


    @FormUrlEncoded
    @POST("api/Appointments/vendor")
    Call<AddAppointmentWithVendorViewModel> addAppointmentWithVendor(@Field("Type") int type, @Field("StartDate") String date,
                                                                     @Field("Address") String address, @Field("Duration") String duration,
                                                                     @Field("Amount") int amount, @Field("PaymentMethod") int paymentMethod, @Field("WantCleaningMatrial") boolean wantCleaningMaterials,
                                                                     @Field("CleaningInstructions") String cleaningInstructions, @Field("AreaID") int areaId,
                                                                     @Field("CityID") int cityId, @Field("PhoneNumber") String phoneNumber,
                                                                     @Field("email") String email, @Field("FirstName") String firstName,
                                                                     @Field("LastName") String lastName,
                                                                     @Header("Authorization") String auth);


}
