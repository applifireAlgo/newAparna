Ext.define('Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.QueAnsModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "queansPkey",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "queid",
          "reference": "Quest",
          "defaultValue": ""
     }, {
          "name": "ansid",
          "reference": "Answer",
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