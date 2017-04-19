package org.chaos.stollbot

import org.chaos.stollbot.discord.Mund
import spock.lang.Specification
import sx.blah.discord.handle.obj.IUser
import sx.blah.discord.handle.obj.StatusType

import java.time.LocalDate

class HirnSpock extends Specification {


    public static final String MOCKED_USERNAME = 'MOCKED_USERNAME'

    def 'Should send a message when a user comes online'() {
        given: 'a Mund'
        Mund mund = Mock(Mund) {
            1 * sendMessage(Hirn.TUER_ZU)
            0 * _
        }

        and: 'a Hirn'
        Hirn hirn = new Hirn(mund: mund)

        and: 'a mocked Discord user'
        IUser user = Mock(IUser) {
            getName() >> MOCKED_USERNAME
        }

        when: 'shoutTuerZu is called'
        hirn.shoutTuerZu(user, oldStatus, StatusType.ONLINE)

        then:
        hirn.gedaechniss.size() == 1

        where:
        oldStatus << [StatusType.IDLE, StatusType.OFFLINE, StatusType.STREAMING, StatusType.DND, StatusType.UNKNOWN]
    }

    def 'Should ignore shouting if status is not changed'() {
        given: 'a Mund'
        Mund mund = Mock(Mund) {
            0 * _
        }

        and: 'a mocked Discord user'
        IUser user = Mock(IUser) {
            0 * getName() >> MOCKED_USERNAME
            0 * _
        }

        and: 'a Hirn'
        Hirn hirn = new Hirn(mund: mund)

        when: 'shoutTuerZu is called'
        hirn.shoutTuerZu(user, status, status)

        then:
        hirn.gedaechniss.size() == 0

        where:
        status << StatusType.values()
    }

    def 'Should send only one message when a user comes online twice a day'() {
        given: 'a Mund'
        Mund mund = Mock(Mund) {
            1 * sendMessage(Hirn.TUER_ZU)
            0 * _
        }

        and: 'a Hirn'
        Hirn hirn = new Hirn(mund: mund)

        and: 'a mocked Discord user'
        IUser user = Mock(IUser) {
            2 * getName() >> MOCKED_USERNAME
            0 * _
        }

        when: 'shoutTuerZu is called'
        hirn.shoutTuerZu(user, oldStatus, StatusType.ONLINE)
        hirn.shoutTuerZu(user, oldStatus, StatusType.ONLINE)

        then:
        hirn.gedaechniss.size() == 1

        where:
        oldStatus << [StatusType.IDLE, StatusType.OFFLINE, StatusType.STREAMING, StatusType.DND, StatusType.UNKNOWN]
    }

    def 'Should remove old entrys from cache when they are not from today'() {
        given: 'a Mund'
        Mund mund = Mock(Mund) {
            1 * sendMessage(Hirn.TUER_ZU)
            0 * _
        }

        and: 'a gedaechniss with old entries'
        Map<String, LocalDate> cache = [
                'MOCKED_STUFF'  : LocalDate.MIN,
                'MOCKED_STUFF_2': LocalDate.MIN
        ]

        and: 'a Hirn'
        Hirn hirn = new Hirn(
                mund: mund,
                gedaechniss: cache
        )

        and: 'a mocked Discord user'
        IUser user = Mock(IUser) {
            1 * getName() >> MOCKED_USERNAME
            0 * _
        }

        when: 'shoutTuerZu is called'
        hirn.shoutTuerZu(user, oldStatus, StatusType.ONLINE)

        then:
        hirn.gedaechniss.size() == 1

        where:
        oldStatus << [StatusType.IDLE, StatusType.OFFLINE, StatusType.STREAMING, StatusType.DND, StatusType.UNKNOWN]
    }
}
