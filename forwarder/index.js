const express = require('express')
const axios = require('axios')
const app = express()

const PORT = 3000;
const FROWARDER_URL = ""
app.all("/forward", async (req, res) => {
  try {
    const { method, url, headers, body } = req;

    // Forward the request to the desired destination
    const response = await axios({
      method,
      url: FROWARDER_URL,
      headers,
      data: body,
    });

    // Forward the response back to the client
    res.status(response.status).send(response.data);
  } catch (error) {
    // Handle any errors that occurred during forwarding
    res.status(500).send("Error forwarding the request");
  }
});

// Start the server
app.listen(PORT, () => {
  console.log(`Forwarder API server listening on port ${PORT}`);
});