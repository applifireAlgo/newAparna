Ext.define('Newproject.view.databrowsercalendar.DBCalendar', {
	extend : 'Newproject.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Newproject.view.databrowsercalendar.DBCalendarController',
	             'Newproject.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
