'use strict';

(function() {

$(document).ready(function () {

    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Форма ' + recipient + ' товара')
        // modal.find('.modal-body input').val(recipient)
    });

    var container = document.querySelector('.table tbody');
    var form = document.querySelector('.needs-validation');
    var editGlobalID;
    var pathURL = 'http://localhost:8080/StockAccountingService-0.0.1-SNAPSHOT/product';

    loadItems ();

    function loadItems () {
        $.ajax({
            type: "GET",
            url: pathURL + "/all",
            contentType: 'application/json',
            dataType: "json",
            crossOrigin: true,
            success: function (response) {
                parseSuccess(response);
            }
        });
    }

    function parseSuccess(data) {
        var n;
        data.filter(function(item) {
            addTr(item.id, item.name, item.description, item.createDate, item.placeStorage, item.reserved);
        });
    }

    function parseFind(data) {
        $('#name').val(data.name);
        $('#desc').val(data.description);
        $('#date').val(timeConverterHTML(data.createDate));
        $('#place').val(data.placeStorage);
        $('#check').prop('checked', data.reserved);
    }

    function validateForm() {

          form.addEventListener('submit', function(event) {
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
            }
            form.classList.add('was-validated');
          }, false);
    }

    function addTr(id, name, desc, date, storage, reserve) {
        var convertDate = timeConverter(date);
        var res = parseReserved (reserve);
        var btnDel = addBtnDelete (id);
        var btnEdit = addBtnEdit(id);
        var str = '<tr>' + '<td>' + id + '</td>' + '<td>' + name + '</td>' + '</td>' + '<td>' + desc + '</td>' + '<td>' + convertDate + '</td>' + '<td>' + storage + '</td>' + '<td>' + res + '</td>' + '<td>'+btnEdit+'</td>' + '<td>'+btnDel+'</td>' + '</tr>';
        $('.table tbody').append(str);
    }

    function addBtnDelete (id) {
        return '<button type="button" class="btn btn-danger delete" data-id="'+ id +'">Удалить</button>';
    }

    function addBtnEdit(id) {
        return '<button type="button" class="btn btn-link edit" data-toggle="modal" data-target="#exampleModal" data-whatever="изменения" data-id="' + id + '">Изменить</button>';
    }

    $('.add-item-table').on('click', function() {
        cleanForm();
        $('.change-btn').removeClass('edit-item');
        $('.change-btn').addClass('add-item');
    });

    $('.container').on('click', '.add-item', function() {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        } else {
            event.preventDefault();
           addItem();
        }
        form.classList.add('was-validated');
    });

    $('.container').on('click', '.edit-item', function() {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        } else {
            event.preventDefault();
           editItem(editGlobalID);
        }
        form.classList.add('was-validated');
    });

    $('.table').on('click', '.edit', function() {
        cleanForm();
        $('.change-btn').removeClass('add-item');
        $('.change-btn').addClass('edit-item');
        var id = $(this).data('id');
        editGlobalID = id;
        $.ajax({
            type: "GET",
            url: pathURL + "/find/" + id,
            contentType: 'application/json',
            dataType: "json",
            crossOrigin: true,
            success: function (response) {
                console.log(response);
                parseFind(response);
            }
        });
    });

    $('.table').on('click', '.delete', function() {
        var id = $(this).data('id');
        $.ajax({
            type: "DELETE",
            url: pathURL + "/remove/" + id,
            contentType: 'application/json',
            dataType: "json",
            crossOrigin: true,
            success: function (response) {
                container.innerHTML = '';
                loadItems ();
                setTimeout(function(){
                    alert('Товар с id: ' + response + ' удален!');
                }, 500);
            }
        });
    });

    function editItem(idEl) {
        var data = {
            id: idEl,
            name: $('#name').val(),
            description: $('#desc').val(),
            create_date: toUnix($('#date').val()),
            place_storage: +$('#place').val(),
            reserved: $('#check').prop('checked')
        };
        console.log(JSON.stringify(data));
        $.ajax({
            type: "PUT",
            url: pathURL + "/update/" + idEl,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            //crossOrigin: true,
            success: function (response) {
                console.log(response);
                container.innerHTML = '';
                loadItems ();
                $('#exampleModal').modal('hide');
                cleanForm();
                setTimeout(function(){
                    alert('Товар с id: ' + response.id + ' изменен!');
                }, 500);
            }
        });
    }

    function addItem() {
        var data = {
            name: $('#name').val(),
            description: $('#desc').val(),
            create_date: toUnix($('#date').val()),
            place_storage: +$('#place').val(),
            reserved: $('#check').prop('checked')
        };
        console.log(JSON.stringify(data));
        $.ajax({
            type: "POST",
            url: pathURL + "/save",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            //crossOrigin: true,
            success: function (response) {
                container.innerHTML = '';
                loadItems ();
                $('#exampleModal').modal('hide');
                cleanForm();
                setTimeout(function(){
                    alert('Товар с id: ' + response.id + ' добавлен!');
                }, 500);
            }
        });
    }

    function toUnix(current) {
        return Date.parse(current) / 1000.0;
    }

    function cleanForm() {
        $('.container').on('hidden.bs.modal', function () {
            $(this).find('form').trigger('reset');
        })
        form.classList.remove('was-validated');
    }

    function parseReserved (reserve) {
        var check = '';
        if (reserve) {
            check = 'checked';
        }
        return '<input type="checkbox" disabled ' + check + '>';
    }

    // Конвертер даты
    function timeConverter(UNIX_timestamp){
      var a = new Date(UNIX_timestamp * 1000);
      var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
      var year = a.getFullYear();
      var month = months[a.getMonth()];
      var date = a.getDate();
      var hour = a.getHours();
      hour = ("0" + hour).slice(-2);
      var min = a.getMinutes();
      min = ("0" + min).slice(-2);
      //var sec = a.getSeconds();
      var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min ;
      return time;
    }

    function timeConverterHTML(UNIX_timestamp){
      var a = new Date(UNIX_timestamp * 1000);
      var year = a.getFullYear();
      var month = a.getMonth();
      month = ("0" + (month + 1)).slice(-2);
      var date = a.getDate();
      date = ("0" + date).slice(-2);
      var hour = a.getHours();
      hour = ("0" + hour).slice(-2);
      var min = a.getMinutes();
      min = ("0" + min).slice(-2);
      var time = year + '-' + month + '-' + date + 'T' + hour + ':' + min;
      return time;
    }
});

})();
