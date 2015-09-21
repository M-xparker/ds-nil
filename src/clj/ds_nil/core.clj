(ns ds-nil.core
  (:require [compojure.core :refer :all]
            [ring.util.response :refer :all]
            [ring.adapter.jetty :as ring]
            [cognitect.transit :as transit]
            [hiccup.core :as hiccup]
            [ring.middleware.resource :refer [wrap-resource]])
  (:import (java.io ByteArrayOutputStream)))

(defn serialize [data]
  (let [out    (ByteArrayOutputStream. 4096)
        writer (transit/writer out :json)
        _      (transit/write writer data)]
    (.toString out)))

(defn data [ctx]
  (response
    (hiccup/html
      [:html
       [:head]
       [:body
        [:script {:id "data" :type "application/transit+json"} (serialize {:users [{:user/id "123"
                                                                                    :user/name "Test"}]})]
        [:script {:src "/js/compiled/ds_nil.js"}]]])))
(defroutes app-routes
           (GET "/" [] data))
(def app (-> app-routes
             (wrap-resource "public")))
(defn start [port]
  (ring/run-jetty app {:port  port
                       :join? false}))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))