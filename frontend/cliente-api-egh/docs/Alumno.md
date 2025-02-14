

# Alumno


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** |  |  [optional] |
|**nombreCompleto** | **String** |  |  |
|**ciclo** | [**CicloEnum**](#CicloEnum) |  |  [optional] |
|**evaluacion** | [**EvaluacionEnum**](#EvaluacionEnum) |  |  [optional] |
|**año** | **Integer** |  |  [optional] |
|**tutor** | [**Tutor**](Tutor.md) |  |  [optional] |
|**empresa** | [**Empresa**](Empresa.md) |  |  [optional] |
|**registrosPracticas** | [**List&lt;RegistroPracticas&gt;**](RegistroPracticas.md) |  |  [optional] |



## Enum: CicloEnum

| Name | Value |
|---- | -----|
| DAM | &quot;DAM&quot; |
| DAW | &quot;DAW&quot; |
| ASIR | &quot;ASIR&quot; |



## Enum: EvaluacionEnum

| Name | Value |
|---- | -----|
| MARZO | &quot;Marzo&quot; |
| SEPTIEMBRE | &quot;Septiembre&quot; |



