Ext.define('Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.FoodModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "foodId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "foodName",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "cuisineName",
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