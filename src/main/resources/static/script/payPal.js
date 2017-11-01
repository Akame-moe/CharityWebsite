paypal.Button.render({

    env: 'sandbox', // Or 'sandbox'

    client: {
        sandbox: 'AZ4rzAJMd3YLKr5-Av9DuqvF0LdsnIN65aRFQpbNF78-jATGOCKDKHQ9Idk0yIXZuX2E7kJ-xqkp3yI1',
        production: 'xxxxxxxxx'
    },

    commit: true, // Show a 'Pay Now' button

    payment: function (data, actions) {
        return actions.payment.create({
            payment: {
                transactions: [
                    {
                        amount: {total: $('#inputDonationAmount').val(), currency: 'GBP'}
                    }
                ]
            }
        });
    },

    onAuthorize: function (data, actions) {
        return actions.payment.execute().then(function (payment) {
            $('#donateModal').modal('toggle');
            var paypalPayment = {
                "id": payment.id,
                "charityId": $('#charityIdDiv').html(),
                "amount": payment.transactions[0].amount.total
            };
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/article",
                data: JSON.stringify(paypalPayment),
                success: function () {
                    console.log("Payment logged.");
                },
                error: function (e) {
                    console.log("Payment not logged.");
                    console.log("ERROR : ", e);
                }
            });
            $('#donateSuccessModal').on('hidden.bs.modal', function (e) {
                location.reload();
            }).modal('toggle');
        });
    }

}, '#paypal-button');
