package com.quviservicestechnician.data.response

import androidx.annotation.Keep
import com.quviservicestechnician.data.repository.PendingBookingResponse
import java.io.Serializable

@Keep
data class BookingResultsResponse(
    val complete: List<CompleteBookingResponse>,
    val inprogress: List<InprogresCompleteResponse>,
    val pending: List<PendingBookingResponse>
) : Serializable