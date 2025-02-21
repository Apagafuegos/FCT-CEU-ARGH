/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * Fecha
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-02-14T16:54:04.731981300+01:00[Europe/Madrid]", comments = "Generator version: 7.11.0")
public class Fecha {
  public static final String SERIALIZED_NAME_UUID = "uuid";
  @SerializedName(SERIALIZED_NAME_UUID)
  @javax.annotation.Nullable
  private Integer uuid;

  public static final String SERIALIZED_NAME_FECHA = "fecha";
  @SerializedName(SERIALIZED_NAME_FECHA)
  @javax.annotation.Nullable
  private LocalDate fecha;

  public static final String SERIALIZED_NAME_AñO = "año";
  @SerializedName(SERIALIZED_NAME_AñO)
  @javax.annotation.Nullable
  private Integer año;

  /**
   * Gets or Sets evaluacion
   */
  @JsonAdapter(EvaluacionEnum.Adapter.class)
  public enum EvaluacionEnum {
    MARZO("MARZO"),
    
    SEPTIEMBRE("SEPTIEMBRE");

    private String value;

    EvaluacionEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static EvaluacionEnum fromValue(String value) {
      for (EvaluacionEnum b : EvaluacionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<EvaluacionEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final EvaluacionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public EvaluacionEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return EvaluacionEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      String value = jsonElement.getAsString();
      EvaluacionEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_EVALUACION = "evaluacion";
  @SerializedName(SERIALIZED_NAME_EVALUACION)
  @javax.annotation.Nullable
  private EvaluacionEnum evaluacion;

  public Fecha() {
  }

  public Fecha uuid(@javax.annotation.Nullable Integer uuid) {
    this.uuid = uuid;
    return this;
  }

  /**
   * Get uuid
   * @return uuid
   */
  @javax.annotation.Nullable
  public Integer getUuid() {
    return uuid;
  }

  public void setUuid(@javax.annotation.Nullable Integer uuid) {
    this.uuid = uuid;
  }


  public Fecha fecha(@javax.annotation.Nullable LocalDate fecha) {
    this.fecha = fecha;
    return this;
  }

  /**
   * Get fecha
   * @return fecha
   */
  @javax.annotation.Nullable
  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(@javax.annotation.Nullable LocalDate fecha) {
    this.fecha = fecha;
  }


  public Fecha año(@javax.annotation.Nullable Integer año) {
    this.año = año;
    return this;
  }

  /**
   * Get año
   * @return año
   */
  @javax.annotation.Nullable
  public Integer getAño() {
    return año;
  }

  public void setAño(@javax.annotation.Nullable Integer año) {
    this.año = año;
  }


  public Fecha evaluacion(@javax.annotation.Nullable EvaluacionEnum evaluacion) {
    this.evaluacion = evaluacion;
    return this;
  }

  /**
   * Get evaluacion
   * @return evaluacion
   */
  @javax.annotation.Nullable
  public EvaluacionEnum getEvaluacion() {
    return evaluacion;
  }

  public void setEvaluacion(@javax.annotation.Nullable EvaluacionEnum evaluacion) {
    this.evaluacion = evaluacion;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Fecha fecha = (Fecha) o;
    return Objects.equals(this.uuid, fecha.uuid) &&
        Objects.equals(this.fecha, fecha.fecha) &&
        Objects.equals(this.año, fecha.año) &&
        Objects.equals(this.evaluacion, fecha.evaluacion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fecha, año, evaluacion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Fecha {\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
    sb.append("    año: ").append(toIndentedString(año)).append("\n");
    sb.append("    evaluacion: ").append(toIndentedString(evaluacion)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("uuid");
    openapiFields.add("fecha");
    openapiFields.add("año");
    openapiFields.add("evaluacion");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Fecha
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Fecha.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Fecha is not found in the empty JSON string", Fecha.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Fecha.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Fecha` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("evaluacion") != null && !jsonObj.get("evaluacion").isJsonNull()) && !jsonObj.get("evaluacion").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `evaluacion` to be a primitive type in the JSON string but got `%s`", jsonObj.get("evaluacion").toString()));
      }
      // validate the optional field `evaluacion`
      if (jsonObj.get("evaluacion") != null && !jsonObj.get("evaluacion").isJsonNull()) {
        EvaluacionEnum.validateJsonElement(jsonObj.get("evaluacion"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Fecha.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Fecha' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Fecha> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Fecha.class));

       return (TypeAdapter<T>) new TypeAdapter<Fecha>() {
           @Override
           public void write(JsonWriter out, Fecha value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Fecha read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Fecha given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Fecha
   * @throws IOException if the JSON string is invalid with respect to Fecha
   */
  public static Fecha fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Fecha.class);
  }

  /**
   * Convert an instance of Fecha to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

