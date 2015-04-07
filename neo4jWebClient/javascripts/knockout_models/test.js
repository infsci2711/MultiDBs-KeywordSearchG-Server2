var restBaseUrl = "http://localhost:7645/";

function PersonViewModel(firstName) {
	var self = this;
	self.firstName = ko.observable(firstName);
}

function PersonsViewModel() {
	var self = this;

	self.people = ko.observableArray();

	self.newPerson = ko.observable(new PersonViewModel());

	self.findAll = function() {
		$.ajax({
			url: restBaseUrl + "Demo1/helloWorld",
			type: 'GET',
			dataType: 'json',
			contentType: "application/json",
			crossDomain: true,
			success: function(data) {
				//self.people.removeAll();
				for (var i = 0; i < data.length; i++) {
					var person = new PersonViewModel(data[i].name);                  
					self.people.push(person);
				}
			},
			error: function(data) {
				alert("Something went wrong while getting persons list. Please try again.");
			}
		});
	};
	self.findAll();
}

ko.applyBindings(new PersonsViewModel(), $("#personsContainer")[0]);