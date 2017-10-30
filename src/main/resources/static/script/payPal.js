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
            $('#donateSuccessModal').modal('toggle');
            //TODO add call to backend with charity id and amount
        });
    }

}, '#paypal-button');
