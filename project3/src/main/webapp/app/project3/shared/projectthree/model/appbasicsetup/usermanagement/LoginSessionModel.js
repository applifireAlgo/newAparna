Ext.define('Project3.project3.shared.projectthree.model.appbasicsetup.usermanagement.LoginSessionModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "appSessionId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "userid",
          "reference": "User",
          "defaultValue": ""
     }, {
          "name": "appServerSessionId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "loginTime",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "lastAccessTime",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "logoutTime",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "clientIPAddress",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "clientIPAddressInt",
          "type": "number",
          "defaultValue": ""
     }, {
          "name": "clientNetworkAddress",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "clientBrowser",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "sessionData",
          "type": "auto",
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