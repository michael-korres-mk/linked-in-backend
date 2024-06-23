package com.licnosoft.linkedin.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponseDTO {

   @JsonProperty("access_token")
   private String token;

   @JsonProperty("token_type")
   private String type;

   @JsonProperty("expires_in")
   private Integer expiresIn;
   private String scope;

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Integer getExpiresIn() {
      return expiresIn;
   }

   public void setExpiresIn(Integer expiresIn) {
      this.expiresIn = expiresIn;
   }

   public String getScope() {
      return scope;
   }

   public void setScope(String scope) {
      this.scope = scope;
   }
}