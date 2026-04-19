import axios from "axios";
import { data } from "react-router-dom";

const api = axios.create({
    baseURL : "http://localhost:2424"
})

api.interceptors.request.use(
    (config) =>{
        const token = localStorage.getItem("token");

        if(token){
            config.headers.Authorization = `Bearer ${token}`
        }
        return config;
    },
    (error) => Promise.reject(error)
);

api.interceptors.response.use(
    (response) => response,
    (error) =>{
        console.error("API ERROR:", error.response || error.message);
        return Promise.reject(
            error.response?.data || "Something went wrong"
        )
    }
)

export default class ApiSerevice{
   
    static async registerUser(registration){
       const res =  await api.post("/auth/register",registration);
       return res.data;
    }

    static async loginUser (data) {
        const res = await api.post("/auth/login", data);
        return res.data;

    }

   static async getLoggedInUserInfo(){
    const response = await api.post("/user");
    return response;
   }

   static async addProduct(fromData){
    const response = await api.post("/product")
   }
}