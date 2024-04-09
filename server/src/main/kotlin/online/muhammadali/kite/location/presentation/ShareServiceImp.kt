package online.muhammadali.kite.location.presentation

import online.muhammadali.kite.location.domain.models.ShareService
import online.muhammadali.kite.location.domain.models.SharedData
import online.muhammadali.kite.location.domain.models.Subscriber

class ShareServiceImp(override val onDataShared: (SharedData, List<Subscriber>) -> Unit) : ShareService() {
}