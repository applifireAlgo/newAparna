Ext.define('Project3.project3.web.projectthree.view.encodecoboundedcontext.newdomain.PartUI', {
     "xtype": "partUIView",
     "items": [{
          "xtype": "displayfield",
          "fieldLabel": "ansId",
          "margin": 5,
          "bindable": "ansId",
          "style": "word-break: break-word; word-wrap: break-word;",
          "name": "ansId",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "ansId_displayfield"
     }, {
          "xtype": "displayfield",
          "fieldLabel": "ansName",
          "margin": 5,
          "bindable": "ansName",
          "style": "word-break: break-word; word-wrap: break-word;",
          "name": "ansName",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "ansName_displayfield"
     }, {
          "xtype": "displayfield",
          "fieldLabel": "ansWei",
          "margin": 5,
          "bindable": "ansWei",
          "style": "word-break: break-word; word-wrap: break-word;",
          "name": "ansWei",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "ansWei_displayfield"
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "dockedItems": [],
     "itemId": "form_ext_28197",
     "extend": "Ext.form.Panel",
     "requires": ["Project3.project3.web.projectthree.controller.encodecoboundedcontext.newdomain.PartUIController", "Project3.project3.shared.projectthree.viewmodel.encodecoboundedcontext.newdomain.PartUIViewModel", "Project3.project3.shared.projectthree.model.encodecoboundedcontext.newdomain.PartUIModel"],
     "viewModel": "PartUIViewModel",
     "controller": "PartUIController"
});