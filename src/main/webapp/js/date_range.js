function formattedDate(date, options) {
    return date.toLocaleString('ru', options).split('.').reverse().join('-');
}

function getDepartureDate(date) {
    return new Date(date.setFullYear(
        date.getFullYear(),
        date.getMonth(),
        date.getDate() + 1
    ));
}

function dateInputs() {
    let dateInputs = $('.form-control--date');
    let optionsFormattedDate = {
        year: 'numeric',
        month: 'numeric',
        day: 'numeric'
    };
    let dateNow = new Date();

    dateInputs.each(function () {
        let currentItem = $(this);

        if (currentItem.attr('name') === 'date_to') {
            let defaultDepartureDate = getDepartureDate(dateNow);
            let formattedDepartureDate = formattedDate(defaultDepartureDate, optionsFormattedDate);

            currentItem.attr('min', formattedDepartureDate);
            currentItem.val(formattedDepartureDate);
        } else {
            let defaultArrivalDate = formattedDate(dateNow, optionsFormattedDate);

            currentItem.attr('min', defaultArrivalDate);
            currentItem.val(defaultArrivalDate);
        }
    });

    dateInputs.on('change', function () {
        let inputName = $(this).attr('name');
        let value = $(this).val();

        if (inputName === 'date_from') {
            let departureInput = $('.form-control--date[name="date_to"]');
            let arrivalDate = new Date(value);
            let departureNextDate = getDepartureDate(arrivalDate);
            let formattedDepartureNextDate = formattedDate(departureNextDate, optionsFormattedDate);

            departureInput.attr('min', formattedDepartureNextDate);
            departureInput.val(formattedDepartureNextDate);
        } else {
            return false;
        }
    });
}