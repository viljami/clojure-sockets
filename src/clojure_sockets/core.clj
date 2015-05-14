(ns clojure-sockets.core
  (:gen-class)
  (:use org.httpkit.server)
  (:require [selmer.parser :refer [render-file]]))

;; Simple HTTP server
(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (render-file "templates/index.html" {:name "World"})
  })

;; Simple Socket Server
(defn async-handler [ring-request]
  ;; unified API for WebSocket and HTTP long polling/streaming
  (with-channel ring-request channel    ; get the channel
    (if (websocket? channel)            ; if you want to distinguish them
      (on-receive channel (fn [data]     ; two way communication
                            (println data)
                            (send! channel (clojure.string/join [data "!!!"]))))
      (send! channel {:status 200
                      :headers {"Content-Type" "text/plain"}
                      :body    "Long polling?"}))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Loading servers...")
  (run-server app {:port 8080})
  (run-server async-handler {:port 9090})
  (println "HTTP Server started at: http://localhost:8080")
  (println "Socket Server started at: ws://localhost:9090"))
