Ext.define('Newproject.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Newproject.view.appreportui.ReportViewController',
	            'Newproject.view.appreportui.datagrid.DataGridPanel',
	            'Newproject.view.appreportui.datagrid.DataGridView',
	            'Newproject.view.appreportui.querycriteria.QueryCriteriaView',
	            'Newproject.view.appreportui.chart.ChartView',
	            'Newproject.view.appreportui.datapoint.DataPointView',
	            'Newproject.view.googlemaps.map.MapPanel',
	            'Newproject.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
