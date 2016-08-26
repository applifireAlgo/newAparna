Ext.define('Newproject.newproject.shared.com.model.appbasicsetup.aaa.PublicApiModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "apiId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "apiData",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "schedulerDetails",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});